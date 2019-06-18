package com.sun.trade_system.service;

import com.sun.trade_system.entity.SystemBank;

/**
 * @Auther: 喻湘东
 * @Email: Jyxd1997@163.com
 * @Date: 2019-06-17 17:07:36
 * @Description:
 */
public interface BankService {
    String addBankInfo(SystemBank systemBank);

    SystemBank getBankByName(String bankName);
}
