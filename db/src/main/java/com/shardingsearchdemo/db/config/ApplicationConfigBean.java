package com.shardingsearchdemo.db.config;

import com.shardingsearchdemo.db.config.sharding.DemoDataSourceConfig;
import com.shardingsearchdemo.db.config.sharding.DemoModeConfiguration;
import com.shardingsearchdemo.db.config.sharding.DynamicDataSourceConfig;
import com.shardingsearchdemo.db.utils.SpringUtils;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * @author patrick_peng
 * @description Bean配置注入类
 * @date 2023-01-09 22:14
 **/
@Configuration
public class ApplicationConfigBean {

    @Bean(value = "springUtils")
    @ConditionalOnMissingBean
    public SpringUtils SpringUtils(){
        return new SpringUtils();
    }

    @Bean
    @ConditionalOnMissingBean
    public RefreshProperties refreshProperties(){
        return new RefreshProperties();
    }

    @Bean
    @ConditionalOnMissingBean
    public DemoModeConfiguration demoModeConfiguration(){
        return new DemoModeConfiguration();
    }

    @Bean
    @ConditionalOnMissingBean
    public DynamicDataSourceConfig dynamicDataSourceConfig(){
        return new DynamicDataSourceConfig();
    }

    @Bean
    @Primary
    @ConditionalOnMissingBean
    @DependsOn(value = "springUtils")
    public DataSource dataSource() throws SQLException {
        return DemoDataSourceConfig.getDataSource();
    }
}
