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
 * @Date: 2019-06-17 16:49:01
 * @Description:
 */
@TableName("t_system_bank")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class SystemBank {
    @TableId("id")
    private String id;
    @TableField("bank_name")
    private String bankName;
    @TableField("bank_address")
    private String bankAddress;
    @TableField("bank_create_time")
    private Date bankCreateTime;

}
