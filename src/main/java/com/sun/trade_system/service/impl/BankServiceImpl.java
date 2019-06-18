package com.sun.trade_system.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.sun.trade_system.dao.BankMapper;
import com.sun.trade_system.entity.SystemBank;
import com.sun.trade_system.service.BankService;
import com.sun.trade_system.util.IdGenerator;
import com.sun.trade_system.util.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @Auther: 喻湘东
 * @Email: Jyxd1997@163.com
 * @Date: 2019-06-17 17:08:19
 * @Description:
 */
@Service
@Slf4j
public class BankServiceImpl implements BankService {

    @Autowired
    private BankMapper bankMapper;

    @Override
    public String addBankInfo(SystemBank systemBank) {
        systemBank.setId(IdGenerator.get());
        systemBank.setBankCreateTime(new Date());
        if(bankMapper.insert(systemBank)==1){
            return JsonUtil.okResult("银行信息添加成功",null);
        }
        return JsonUtil.errorResult("银行信息添加失败");
    }

    @Override
    public SystemBank getBankByName(String bankName) {
        return bankMapper.getBankByName(bankName);
    }

}
