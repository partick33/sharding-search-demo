package com.shardingsearchdemo.db.config;

import com.shardingsearchdemo.db.config.entity.DataSourceForm;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author patrick_peng
 * @description 动态配置
 * @date 2023-01-09 23:26
 **/
@RefreshScope
@Data
@Component
@ConfigurationProperties(prefix = "demo.sharding-search")
public class RefreshProperties {
    /**
     * 分库分表动态数据源
     */
    private List<DataSourceForm> dataSources;

    /**
     * 分库分表模式配置
     */
    private String modeType;

    /**
     * 逻辑库名
     */
    private String databaseName;
}
