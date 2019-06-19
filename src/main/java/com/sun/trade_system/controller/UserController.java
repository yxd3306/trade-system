package com.sun.trade_system.controller;

import com.sun.trade_system.entity.SystemUser;
import com.sun.trade_system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther: 喻湘东
 * @Email: Jyxd1997@163.com
 * @Date: 2019-06-17 17:05:30
 * @Description:
 */
@RestController
@RequestMapping("/system/bank/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/create")
    public String openAccount(SystemUser systemUser,String password){
        return userService.openAccount(systemUser,password);
    }

    @GetMapping("/addMoney")
    public String addMoney(String bankCardNumber,Double money){
        return userService.addMoney(bankCardNumber,money);
    }


    /**
     * 转账接口
     * @param from   出账用户卡
     * @param to     入账用户卡
     * @param money  入账金额
     * @return
     */
    @GetMapping("/transaction")
    public String userTransaction(String from,String to,Double money){
        return userService.userTransaction(from,to,money);
    }

}
