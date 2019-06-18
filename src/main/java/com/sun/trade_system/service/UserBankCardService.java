package com.sun.trade_system.service;

import com.sun.trade_system.entity.SystemUserBankCard;

/**
 * @Auther: 喻湘东
 * @Email: Jyxd1997@163.com
 * @Date: 2019-06-17 18:10:13
 * @Description:
 */
public interface UserBankCardService {
    void createUserBankCard(SystemUserBankCard systemUserBankCard);

    SystemUserBankCard findByCardNumber(String bankCardNumber);
}
