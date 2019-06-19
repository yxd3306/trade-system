package com.sun.trade_system.service.impl;

import com.sun.trade_system.annotation.Lock;
import com.sun.trade_system.annotation.Log;
import com.sun.trade_system.annotation.TransactionLog;
import com.sun.trade_system.dao.UserMapper;
import com.sun.trade_system.entity.SystemBank;
import com.sun.trade_system.entity.SystemBankCard;
import com.sun.trade_system.entity.SystemUser;
import com.sun.trade_system.entity.SystemUserBankCard;
import com.sun.trade_system.service.BankCardService;
import com.sun.trade_system.service.BankService;
import com.sun.trade_system.service.UserBankCardService;
import com.sun.trade_system.service.UserService;
import com.sun.trade_system.util.DoubleUtil;
import com.sun.trade_system.util.IdGenerator;
import com.sun.trade_system.util.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;
import org.springframework.util.DigestUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * @Auther: 喻湘东
 * @Email: Jyxd1997@163.com
 * @Date: 2019-06-17 17:40:24
 * @Description:
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private BankService bankService;
    @Autowired
    private BankCardService bankCardService;
    @Autowired
    private UserBankCardService userBankCardService;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private TransactionTemplate transactionTemplate;

    @Override
    public String openAccount(SystemUser systemUser,String password) {

        String bankName = systemUser.getOpenAccountBankName();
        SystemBank systemBank = bankService.getBankByName(bankName);
        String userId=IdGenerator.get();
        Date createTime = new Date();
        systemUser.setId(userId);
        systemUser.setOpenAccountBankId(systemBank.getId());
        systemUser.setOpenAccountBankAddress(systemBank.getBankAddress());
        systemUser.setOpenAccountStatus(1);
        systemUser.setOpenAccountCreateTime(createTime);
        if(userMapper.insert(systemUser)==1){
            // 生成
            SystemBankCard systemBankCard = new SystemBankCard();
            systemBankCard.setId(IdGenerator.get());
            Date date = new Date();
            SimpleDateFormat format = new SimpleDateFormat("yyyy");
            // 6217002920129623089
            String bankCardNumber = "621700"+getRandomCode(2)+format.format(date)+getRandomCode(7);
            systemBankCard.setBankCardNumber(bankCardNumber);
            password = DigestUtils.md5DigestAsHex(password.getBytes());
            systemBankCard.setBankCardPassword(password);
            systemBankCard.setBankCardUserBalance(0.0);
            systemBankCard.setBankCardType(1);
            systemBankCard.setBankCardStatus(1);
            systemBankCard.setBankCardCreateTime(createTime);
            bankCardService.createBankCard(systemBankCard);

            SystemUserBankCard systemUserBankCard = new SystemUserBankCard();
            systemUserBankCard.setId(IdGenerator.get());
            systemUserBankCard.setBankCardNumber(bankCardNumber);
            systemUserBankCard.setOpenAccountUserId(systemUser.getId());
            systemUserBankCard.setOpenAccountBankId(systemBank.getId());
            systemUserBankCard.setBankCardId(systemBankCard.getId());
            userBankCardService.createUserBankCard(systemUserBankCard);

            return JsonUtil.okResult("开户成功",null);
        }
        return JsonUtil.errorResult("开户失败");
    }

    @Override
    public String addMoney(String bankCardNumber, Double money) {
        SystemBankCard systemBankCard=bankCardService.findBankCardByCardNumber(bankCardNumber);
        if(null!=systemBankCard){
            systemBankCard.setBankCardUserBalance(systemBankCard.getBankCardUserBalance()+money);
            if(bankCardService.updateMoney(systemBankCard)){
                systemBankCard=bankCardService.findBankCardById(systemBankCard.getId());
                return JsonUtil.okResult("存款成功","卡上余额为："+ DoubleUtil.formatDouble(systemBankCard.getBankCardUserBalance()));
            }
        }
        return JsonUtil.errorResult("存款失败，请重试");
    }

    @Lock(key = "transaction")
    @TransactionLog(doWith = "转账记录")
    @Override
    public String userTransaction(String from, String to, Double money) {
        SystemBankCard fromBankCard=bankCardService.findBankCardByCardNumber(from);
        SystemBankCard toBankCard=bankCardService.findBankCardByCardNumber(to);

        // 必须两张卡都不为空
        if(null!=fromBankCard && null!=toBankCard){
            Double fromBalance = fromBankCard.getBankCardUserBalance();
            if(fromBalance<money){
                return JsonUtil.okResult("卡上余额不足","卡上余额为："+DoubleUtil.formatDouble(fromBalance));
            }else{
                fromBankCard.setBankCardUserBalance((double)fromBalance-money);
                toBankCard.setBankCardUserBalance((double)(toBankCard.getBankCardUserBalance()+money));

                boolean flag = transactionTemplate.execute(new TransactionCallback<Boolean>() {
                    @Override
                    public Boolean doInTransaction(TransactionStatus transactionStatus) {
                        boolean b = bankCardService.updateMoney(fromBankCard);
                        boolean b1 = bankCardService.updateMoney(toBankCard);
                        return b==b1;
                    }
                });
                if(flag){
                    return JsonUtil.okResult("转账成功",null);
                }
            }
        }
        return JsonUtil.errorResult("转账失败，非法参数");
    }

    @Override
    public SystemUser findFromUserInfoById(String id) {

        SystemUserBankCard systemUserBankCard = userBankCardService.findByCardNumber(id);
        if(null!=systemUserBankCard){
            // 用户id
            String fromUserId = systemUserBankCard.getOpenAccountUserId();
            SystemUser fromUser = userMapper.findUserById(fromUserId);
            return fromUser;
        }
        return null;
    }

    @Override
    public SystemUser findToUserInfoById(String id) {
        SystemUserBankCard systemUserBankCard = userBankCardService.findByCardNumber(id);
        if(null!=systemUserBankCard){
            // 用户id
            String toUserId = systemUserBankCard.getOpenAccountUserId();
            SystemUser toUser = userMapper.findUserById(toUserId);
            return toUser;
        }
        return null;
    }

    public static String getRandomCode(Integer code){
        Random random = new Random();
        StringBuffer result= new StringBuffer();
        for (int i=0;i<code;i++){
            result.append(random.nextInt(10));
        }
        return result.toString();
    }
}
