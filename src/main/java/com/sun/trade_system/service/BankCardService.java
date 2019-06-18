package com.sun.trade_system.service;

import com.sun.trade_system.entity.SystemBankCard;

/**
 * @Auther: 喻湘东
 * @Email: Jyxd1997@163.com
 * @Date: 2019-06-17 17:54:52
 * @Description:
 */
public interface BankCardService {
    void createBankCard(SystemBankCard systemBankCard);

    SystemBankCard findBankCardById(String cardId);

    boolean updateMoney(SystemBankCard systemBankCard);

    SystemBankCard findBankCardByCardNumber(String cardNumber);
}
