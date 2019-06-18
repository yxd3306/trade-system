package com.sun.trade_system.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Auther: 喻湘东
 * @Email: Jyxd1997@163.com
 * @Date: 2019-06-17 16:50:53
 * @Description:
 */
@TableName("t_system_user_bank_card")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class SystemUserBankCard {
    @TableId("id")
    private String id;
    @TableField("bank_card_number")
    private String bankCardNumber;
    @TableField("open_account_user_id")
    private String openAccountUserId;
    @TableField("open_account_bank_id")
    private String openAccountBankId;
    @TableField("bank_card_id")
    private String bankCardId;
}
