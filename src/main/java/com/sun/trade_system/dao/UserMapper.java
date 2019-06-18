package com.sun.trade_system.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sun.trade_system.entity.SystemUser;
import org.apache.ibatis.annotations.Select;

/**
 * @Auther: 喻湘东
 * @Email: Jyxd1997@163.com
 * @Date: 2019-06-17 17:52:42
 * @Description:
 */
public interface UserMapper extends BaseMapper<SystemUser> {
    @Select("SELECT\n" +
            "\t*\n" +
            "FROM\n" +
            "\tt_system_user\n" +
            "WHERE\n" +
            "\tid=#{id}\n" +
            "AND\n" +
            "\topen_account_status=1")
    SystemUser findUserById(String id);
}
