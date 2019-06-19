package com.sun.trade_system.service;

import com.sun.trade_system.entity.SystemUser;

/**
 * @Auther: 喻湘东
 * @Email: Jyxd1997@163.com
 * @Date: 2019-06-17 17:40:13
 * @Description:
 */
public interface UserService {
    String openAccount(SystemUser systemUser,String password);

    String addMoney(String bankCardNumber, Double money);

    String userTransaction(String from, String to, Double money);

    SystemUser findFromUserInfoById(String id);

    SystemUser findToUserInfoById(String id);
}
