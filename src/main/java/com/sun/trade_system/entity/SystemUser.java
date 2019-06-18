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
 * @Date: 2019-06-17 16:49:39
 * @Description:
 */
@TableName("t_system_user")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class SystemUser {
    @TableId("id")
    private String id;
    @TableField("open_account_bank_id")
    private String openAccountBankId;
    @TableField("open_account_bank_name")
    private String openAccountBankName;
    @TableField("open_account_bank_address")
    private String openAccountBankAddress;
    @TableField("open_account_user_name")
    private String openAccountUserName;
    @TableField("open_account_user_id_number")
    private String openAccountUserIdNumber;
    @TableField("open_account_user_phone")
    private String openAccountUserPhone;
    @TableField("open_account_user_address")
    private String openAccountUserAddress;
    @TableField("open_account_user_age")
    private Integer openAccountUserAge;
    @TableField("open_account_user_sex")
    private String openAccountUserSex;
    @TableField("open_account_create_time")
    private Date openAccountCreateTime;
    @TableField("open_account_status")
    private Integer openAccountStatus;
}
