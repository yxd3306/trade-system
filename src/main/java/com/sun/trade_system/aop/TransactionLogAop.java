package com.sun.trade_system.aop;

import com.sun.trade_system.annotation.Lock;
import com.sun.trade_system.annotation.Log;
import com.sun.trade_system.annotation.TransactionLog;
import com.sun.trade_system.entity.SystemBankLog;
import com.sun.trade_system.entity.SystemUser;
import com.sun.trade_system.service.BankLogService;
import com.sun.trade_system.service.UserService;
import com.sun.trade_system.util.CusAccessObjectUtil;
import com.sun.trade_system.util.IdGenerator;
import com.sun.trade_system.util.JsonUtil;
import com.sun.trade_system.util.LockUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.aspectj.lang.reflect.SourceLocation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.Map;

/**
 * @Auther: 喻湘东
 * @Email: Jyxd1997@163.com
 * @Date: 2019-06-18 14:24:12
 * @Description:
 */
@Aspect
@Component
@Slf4j
public class TransactionLogAop {

    private String logId=IdGenerator.get();

    @Autowired
    private UserService userService;
    @Autowired
    private BankLogService bankLogService;

    // 环绕通知
    @Around("@annotation(transactionLog)")
    public Object doAround(ProceedingJoinPoint pjp, TransactionLog transactionLog) throws Throwable {

        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
        Map<String, String[]> parameterMap = request.getParameterMap();
        String fromCardId = parameterMap.get("from")[0];
        String toCardId = parameterMap.get("to")[0];

        Signature signature = pjp.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        Method method = methodSignature.getMethod();
        Lock lock = method.getDeclaredAnnotation(Lock.class);
        if (null != lock) {
            String key = lock.key();
            // 上锁
            if (LockUtil.tryLock(key, fromCardId + toCardId, 300)) {
                // 获取操作用户
                SystemUser fromUser = userService.findFromUserInfoById(fromCardId);
                // 获取收账用户
                SystemUser toUser = userService.findToUserInfoById(toCardId);
                if (null != fromUser && null != toUser) {
                    return pjp.proceed();

                } else {
                    response.setCharacterEncoding("UTF-8");
                    response.setContentType("application/json; charset=utf-8");
                    PrintWriter out = null;
                    out = response.getWriter();
                    out.append(JsonUtil.errorResult("非法参数"));
                    out.close();
                }
            }else{
                response.setCharacterEncoding("UTF-8");
                response.setContentType("application/json; charset=utf-8");
                PrintWriter out = null;
                out = response.getWriter();
                out.append(JsonUtil.errorResult("系统繁忙请稍后重试"));
                out.close();
            }
        }
        return null;


    }

    // 后置通知 正常运行返回
    @AfterReturning("@annotation(transactionLog)")
    public void doAfterReturning(JoinPoint joinPoint,TransactionLog transactionLog){
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        Map<String, String[]> parameterMap = request.getParameterMap();
        String fromCardId = parameterMap.get("from")[0];
        String toCardId = parameterMap.get("to")[0];

        Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        Method method = methodSignature.getMethod();

        // 获取操作用户
        SystemUser fromUser = userService.findFromUserInfoById(fromCardId);
        // 获取收账用户
        SystemUser toUser = userService.findToUserInfoById(toCardId);

        transactionLog = method.getDeclaredAnnotation(TransactionLog.class);

        // 操作人id
        String userId = fromUser.getId();
        // 操作人姓名
        String openAccountUserName = fromUser.getOpenAccountUserName();
        String bankId = fromUser.getOpenAccountBankId();
        String requestURI = request.getRequestURI();
        String ip = CusAccessObjectUtil.getIpAddress(request);
        Date doTime = new Date();
        // 做什么事
        StringBuffer doWith = new StringBuffer();
        // 谁给谁转账多少钱
        doWith.append(transactionLog.doWith())
                .append("----")
                .append(openAccountUserName)
                .append("-银行卡号：")
                .append(fromCardId)
                .append("转账到：")
                .append(toUser.getOpenAccountUserName())
                .append("-银行卡号：")
                .append(toCardId)
                .append("-金额为：")
                .append(parameterMap.get("money")[0])
                .append("元");
        SystemBankLog systemBankLog = new SystemBankLog();
        systemBankLog.setId(logId);
        systemBankLog.setOpenAccountUserId(userId);
        systemBankLog.setOpenAccountUserName(openAccountUserName);
        systemBankLog.setBankId(bankId);
        systemBankLog.setOperationIp(ip);
        systemBankLog.setOperationMapping(requestURI);
        systemBankLog.setDoWhat(doWith.toString());
        systemBankLog.setCreateTime(doTime);
        systemBankLog.setDoStatus(1);

        bankLogService.writer(systemBankLog);

    }


    // 最终通知 有无异常都执行  用来解锁
    @After("@annotation(transactionLog)")
    public void doAfter(JoinPoint joinPoint,TransactionLog transactionLog){
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        Map<String, String[]> parameterMap = request.getParameterMap();
        String fromCardId = parameterMap.get("from")[0];
        String toCardId = parameterMap.get("to")[0];

        Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        Method method = methodSignature.getMethod();
        Lock lock = method.getDeclaredAnnotation(Lock.class);
        if(null!=lock){
            LockUtil.unlock(lock.key(), fromCardId + toCardId);
        }
    }

    // 异常通知
    @AfterThrowing("@annotation(transactionLog)")
    public void doAfterThrowing(JoinPoint joinPoint, TransactionLog transactionLog) {
        SystemBankLog systemBankLog = bankLogService.findLogById(this.logId);
        if(null!=systemBankLog){
            String doWhat = systemBankLog.getDoWhat();
            doWhat+=",但是失败了";
            systemBankLog.setDoStatus(0);
            systemBankLog.setDoWhat(doWhat);
            bankLogService.updateLogById(systemBankLog);
        }
    }


}
