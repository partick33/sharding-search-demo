package com.shardingsearchdemo.db.utils;

import cn.hutool.core.util.ReflectUtil;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Field;

/**
 * @author patrick_peng
 * @description 反射工具类
 * @date 2023-01-10 21:23
 **/
public class ReflectionUtils {

    public static<T> Field[] getFields(Class<T> t){
        return ReflectUtil.getFields(t);
    }

    public static<T> void setFieldValue(T tar, String fieldName, T value){
        ReflectUtil.setFieldValue(tar, fieldName, value);
    }

    public static<T extends AccessibleObject> void setAccessible(T field){
        ReflectUtil.setAccessible(field);
    }
}
