package com.demo.shardingsearchdemo.config;

import com.demo.shardingsearchdemo.utils.SpringUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author patrick_peng
 * @description Bean配置注入类
 * @date 2023-01-09 22:14
 **/
@Configuration
public class ApplicationConfigBean {

    @Bean
    public SpringUtils SpringUtils(){
        return new SpringUtils();
    }
}
