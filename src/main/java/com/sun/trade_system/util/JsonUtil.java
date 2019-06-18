package com.sun.trade_system.util;

import com.alibaba.fastjson.JSONObject;

/**
 * @Auther: 喻湘东
 * @Email: Jyxd1997@163.com
 * @Date: 2019-06-17 17:12:11
 * @Description:
 */
public class JsonUtil {

    private static final int OK_CODE=200;
    private static final int ERROR_CODE=200;

    public static String okResult(String msg,Object data){
        JSONObject result = new JSONObject();
        result.put("code",OK_CODE);
        result.put("msg",msg);
        if(null!=data){
            result.put("data",data);
        }
        return result.toJSONString();
    }
    public static String errorResult(String msg){
        JSONObject result = new JSONObject();
        result.put("code",ERROR_CODE);
        result.put("msg",msg);
        return result.toJSONString();
    }

}
