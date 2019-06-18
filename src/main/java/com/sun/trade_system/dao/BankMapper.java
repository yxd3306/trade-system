package com.sun.trade_system.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sun.trade_system.entity.SystemBank;
import org.apache.ibatis.annotations.Select;

/**
 * @Auther: 喻湘东
 * @Email: Jyxd1997@163.com
 * @Date: 2019-06-17 17:09:22
 * @Description:
 */
public interface BankMapper extends BaseMapper<SystemBank> {
    @Select("select\n" +
            "        id,\n" +
            "        bank_name,\n" +
            "        bank_address,\n" +
            "        bank_create_time\n" +
            "        from\n" +
            "        t_system_bank\n" +
            "        where\n" +
            "        bank_name=#{bankName}")
    SystemBank getBankByName(String bankName);
}
