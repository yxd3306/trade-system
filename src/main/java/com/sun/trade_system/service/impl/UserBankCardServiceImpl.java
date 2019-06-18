package com.sun.trade_system.service.impl;

import com.sun.trade_system.dao.UserBankCardMapper;
import com.sun.trade_system.entity.SystemUserBankCard;
import com.sun.trade_system.service.UserBankCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Auther: 喻湘东
 * @Email: Jyxd1997@163.com
 * @Date: 2019-06-17 18:10:24
 * @Description:
 */
@Service
public class UserBankCardServiceImpl implements UserBankCardService {

    @Autowired
    private UserBankCardMapper userBankCardMapper;

    @Override
    public void createUserBankCard(SystemUserBankCard systemUserBankCard) {
        userBankCardMapper.insert(systemUserBankCard);
    }

    @Override
    public SystemUserBankCard findByCardNumber(String bankCardNumber) {

        return userBankCardMapper.findByCardId(bankCardNumber);
    }
}
