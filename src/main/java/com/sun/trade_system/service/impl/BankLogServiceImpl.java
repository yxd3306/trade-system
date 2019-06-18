package com.sun.trade_system.service.impl;

import com.sun.trade_system.dao.BankLogMapper;
import com.sun.trade_system.entity.SystemBankLog;
import com.sun.trade_system.service.BankLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Auther: 喻湘东
 * @Email: Jyxd1997@163.com
 * @Date: 2019-06-18 14:41:09
 * @Description:
 */
@Service
public class
BankLogServiceImpl implements BankLogService {

    @Autowired
    private BankLogMapper bankLogMapper;

    @Override
    public void writer(SystemBankLog systemBankLog) {
        bankLogMapper.insert(systemBankLog);
    }

    @Override
    public SystemBankLog findLogById(String id) {
        return bankLogMapper.selectById(id);
    }

    @Override
    public void updateLogById(SystemBankLog systemBankLog) {
        bankLogMapper.updateById(systemBankLog);
    }
}
