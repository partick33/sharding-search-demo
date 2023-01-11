package com.shardingsearchdemo.db.utils;

import cn.hutool.core.date.DateUtil;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * @author patrick_peng
 * @description Mock生成对象工具类
 * @date 2023-01-10 21:21
 **/
@Slf4j
public class MockUtil {
    public static  <T> T getCreateObject(Class<T> cls) throws InstantiationException, IllegalAccessException {
        Field[] fields = ReflectionUtils.getFields(cls);
        T t = cls.newInstance();
        Random random = new Random();
        for (Field field : fields) {
            ReflectionUtils.setAccessible(field);
            if (Long.class.equals(field.getType())){
                ReflectionUtils.setFieldValue(t, field.getName(), (long) random.nextInt(1000));
            }
            if(BigDecimal.class.equals(field.getType())){
                ReflectionUtils.setFieldValue(t, field.getName(), new BigDecimal(random.nextInt(1000)));
            }
            if(Integer.class.equals(field.getType())){
                ReflectionUtils.setFieldValue(t, field.getName(), 0);
            }
            if(Short.class.equals(field.getType())){
                ReflectionUtils.setFieldValue(t, field.getName(),Short.valueOf("0"));
            }
            if(String.class.equals(field.getType())){
                ReflectionUtils.setFieldValue(t, field.getName(),  DateUtil.current() + "自动生成测试用例");
            }
            if(Date.class.equals(field.getType())){
                ReflectionUtils.setFieldValue(t,field.getName(), DateUtil.now());
            }
        }
        return t;
    }

    public static <T> List<T> getCreatObjectList(Class<T> cls, int size) throws InstantiationException, IllegalAccessException {
        log.info("开始生成单元测试实体");
        List<T> list = new ArrayList<>();
        for (int i = 0; i < size; i++){
            T t = getCreateObject(cls);
            list.add(t);
        }
        log.info("结束生成单元测试实体");
        return list;
    }
}
