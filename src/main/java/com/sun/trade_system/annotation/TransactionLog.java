package com.sun.trade_system.annotation;

import java.lang.annotation.*;

/**
 * @Auther: 喻湘东
 * @Email: Jyxd1997@163.com
 * @Date: 2019-06-18 14:25:02
 * @Description:
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface TransactionLog {
    String doWith() default "";
}
