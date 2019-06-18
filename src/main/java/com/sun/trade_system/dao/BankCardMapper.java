package com.sun.trade_system.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sun.trade_system.entity.SystemBankCard;
import org.apache.ibatis.annotations.Select;

/**
 * @Auther: 喻湘东
 * @Email: Jyxd1997@163.com
 * @Date: 2019-06-17 17:59:05
 * @Description:
 */
public interface BankCardMapper extends BaseMapper<SystemBankCard> {
    @Select("SELECT\n" +
            "\tid,\n" +
            "\tbank_card_type,\n" +
            "\tbank_card_number,\n" +
            "\tbank_card_password,\n" +
            "\tbank_card_user_balance,\n" +
            "\tbank_card_create_time,\n" +
            "\tbank_card_status\n" +
            "FROM\n" +
            "\t`t_system_bank_card` \n" +
            "WHERE\n" +
            "\tbank_card_number = #{bankCardNumber}\n" +
            "AND\n" +
            "\tbank_card_status=1")
    SystemBankCard findBankCardByCardNumber(String bankCardNumber);
}
