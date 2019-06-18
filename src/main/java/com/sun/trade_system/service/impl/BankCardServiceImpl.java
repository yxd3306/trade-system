package com.sun.trade_system.service.impl;

import com.sun.trade_system.dao.BankCardMapper;
import com.sun.trade_system.entity.SystemBankCard;
import com.sun.trade_system.service.BankCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Auther: 喻湘东
 * @Email: Jyxd1997@163.com
 * @Date: 2019-06-17 17:58:27
 * @Description:
 */
@Service
public class BankCardServiceImpl implements BankCardService {

    @Autowired
    private BankCardMapper bankCardMapper;

    @Override
    public void createBankCard(SystemBankCard systemBankCard) {
        bankCardMapper.insert(systemBankCard);
    }

    @Override
    public SystemBankCard findBankCardById(String cardId) {
        return bankCardMapper.selectById(cardId);
    }

    @Override
    public boolean updateMoney(SystemBankCard systemBankCard) {
        return bankCardMapper.updateById(systemBankCard)==1?true:false;
    }

    @Override
    public SystemBankCard findBankCardByCardNumber(String bankCardNumber) {
        return bankCardMapper.findBankCardByCardNumber(bankCardNumber);
    }
}
