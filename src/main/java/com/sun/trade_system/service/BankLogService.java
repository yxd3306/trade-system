package com.sun.trade_system.service;

import com.sun.trade_system.entity.SystemBankLog;

/**
 * @Auther: 喻湘东
 * @Email: Jyxd1997@163.com
 * @Date: 2019-06-18 14:40:59
 * @Description:
 */
public interface BankLogService {
    void writer(SystemBankLog systemBankLog);

    SystemBankLog findLogById(String id);

    void updateLogById(SystemBankLog systemBankLog);
}
