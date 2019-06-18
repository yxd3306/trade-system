package com.sun.trade_system.aop;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.annotation.TableField;
import com.sun.trade_system.annotation.Log;
import com.sun.trade_system.entity.SystemBankLog;
import com.sun.trade_system.entity.SystemUser;
import com.sun.trade_system.entity.SystemUserBankCard;
import com.sun.trade_system.service.UserService;
import com.sun.trade_system.util.CusAccessObjectUtil;
import com.sun.trade_system.util.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.omg.CORBA.ServerRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.util.*;

/**
 * @Auther: 喻湘东
 * @Email: Jyxd1997@163.com
 * @Date: 2019-06-18 10:37:31
 * @Description:
 */
@Aspect
@Component
public class LogAop {



}
