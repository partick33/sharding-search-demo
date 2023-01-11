package com.shardingsearchdemo.db.utils;

import cn.hutool.core.util.IdUtil;

/**
 * @author patrick_peng
 * @description UUID工具类
 * @date 2023-01-10 21:40
 **/
public class IdUtils {

    public static String fastSimpleUUID(){
        return IdUtil.fastSimpleUUID();
    }
}
