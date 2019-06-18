package com.sun.trade_system.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @Auther: 喻湘东
 * @Email: Jyxd1997@163.com
 * @Date: 2019-06-04 14:39:10
 * @Description:
 */
@Component
public class MetaObjectHandlerConf  extends MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {

        Object createTime = getFieldValByName("createTime", metaObject);
        Object updateTime = getFieldValByName("updateTime", metaObject);
        if (createTime == null)
            setFieldValByName("createTime", new Date(), metaObject);//mybatis-plus版本2.0.9+
        if (updateTime == null)
            setFieldValByName("updateTime", new Date(), metaObject);//mybatis-plus版本2.0.9+
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        Object updateTime = getFieldValByName("updateTime", metaObject);
        if (updateTime == null) {
            setFieldValByName("updateTime", new Date(), metaObject);//mybatis-plus版本2.0.9+
        }
    }
}
