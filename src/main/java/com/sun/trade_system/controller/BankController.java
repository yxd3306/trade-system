package com.sun.trade_system.controller;

import com.sun.trade_system.entity.SystemBank;
import com.sun.trade_system.service.BankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @Auther: 喻湘东
 * @Email: Jyxd1997@163.com
 * @Date: 2019-06-17 17:05:09
 * @Description:
 */
@Controller
@RequestMapping("/system/bank")
public class BankController {

    @Autowired
    private BankService bankService;

    @GetMapping("/add")
    @ResponseBody
    public String addBankInfo(SystemBank systemBank){
        return bankService.addBankInfo(systemBank);
    }

}
