package com.shardingsearchdemo.db.utils;

import org.jetbrains.annotations.NotNull;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.util.Map;

/**
 * @author patrick_peng
 * @description SpringBean工具类
 * @date 2023-01-09 22:06
 **/
public class SpringUtils implements ApplicationContextAware {

    private static ApplicationContext CONTEXT;

    @Override
    public void setApplicationContext(@NotNull ApplicationContext applicationContext) throws BeansException {
        if (CONTEXT != null) {
            if (applicationContext.getParent () == CONTEXT) {
                SpringUtils.CONTEXT = applicationContext;
            }
        } else {
            SpringUtils.CONTEXT = applicationContext;
        }
    }

    public static ApplicationContext getContext(){
        return CONTEXT;
    }

    public static <E> E getBean(Class<E> clz){
        return getContext().getBean(clz);
    }

    public static<E> Map<String, E> getBeanByType(Class<E> clz){
        return getContext().getBeansOfType(clz);
    }
}
