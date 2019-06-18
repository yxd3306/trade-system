package com.sun.trade_system.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sun.trade_system.entity.SystemUserBankCard;
import org.apache.ibatis.annotations.Select;

/**
 * @Auther: 喻湘东
 * @Email: Jyxd1997@163.com
 * @Date: 2019-06-17 18:11:26
 * @Description:
 */
public interface UserBankCardMapper extends BaseMapper<SystemUserBankCard> {
    @Select("SELECT\n" +
            "\tid,\n" +
            "\tbank_card_number,\n" +
            "\topen_account_user_id,\n" +
            "\topen_account_bank_id,\n" +
            "\tbank_card_id\n" +
            "FROM\n" +
            "\t`t_system_user_bank_card` \n" +
            "WHERE\n" +
            "\tbank_card_number = #{bankCardNumber}")
    SystemUserBankCard findByCardId(String bankCardNumber);
}
