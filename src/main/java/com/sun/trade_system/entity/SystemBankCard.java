package com.sun.trade_system.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @Auther: 喻湘东
 * @Email: Jyxd1997@163.com
 * @Date: 2019-06-17 16:49:23
 * @Description:
 */
@TableName("t_system_bank_card")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class SystemBankCard {
    @TableId("id")
    private String id;
    @TableField("bank_card_type")
    private Integer bankCardType;
    @TableField("bank_card_user_balance")
    private Double bankCardUserBalance; //银行卡余额
    @TableField("bank_card_number")
    private String bankCardNumber;
    @TableField("bank_card_password")
    private String bankCardPassword;
    @TableField("bank_card_create_time")
    private Date bankCardCreateTime;
    @TableField("bank_card_status")
    private Integer bankCardStatus;
}
