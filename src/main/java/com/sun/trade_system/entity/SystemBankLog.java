package com.sun.trade_system.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Period;
import java.util.Date;

/**
 * @Auther: 喻湘东
 * @Email: Jyxd1997@163.com
 * @Date: 2019-06-17 16:49:31
 * @Description: 操作日志表
 */
@TableName("t_system_log")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class SystemBankLog {
    @TableId("id")
    private String id;
    @TableField("open_account_user_id")
    private String openAccountUserId; //操作人id
    @TableField("from_bank_card_id")
    private String fromBankCardId; //操作人id
    @TableField("to_bank_card_id")
    private String toBankCardId; //操作人id
    @TableField("open_account_user_name")
    private String openAccountUserName; //操作人姓名
    @TableField("bank_id")
    private String bankId;
    @TableField("do_what")
    private String doWhat; // 做了什么操作
    @TableField("operation_ip")
    private String operationIp; //客户端ip
    @TableField("operation_mapping")
    private String operationMapping; //操作接口
    @TableField("create_time")
    private Date createTime; //操作时间
    @TableField("do_status")
    private Integer doStatus; //操作时间
    @TableField("do_type")
    private String doType; //操作时间

}
