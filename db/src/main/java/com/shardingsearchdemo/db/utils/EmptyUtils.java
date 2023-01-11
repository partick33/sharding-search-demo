package com.shardingsearchdemo.db.utils;

import org.apache.commons.lang3.ObjectUtils;

/**
 * @author patrick_peng
 * @description 判空工具类
 * @date 2023-01-10 11:32
 **/
public class EmptyUtils {

    public static<T> Boolean isNotNull(T t){
        return ObjectUtils.isNotEmpty(t);
    }
}
