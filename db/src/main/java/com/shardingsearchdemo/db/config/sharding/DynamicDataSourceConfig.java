package com.shardingsearchdemo.db.config.sharding;

import com.shardingsearchdemo.db.config.RefreshProperties;
import com.shardingsearchdemo.db.config.entity.DataSourceForm;
import com.shardingsearchdemo.db.utils.EmptyUtils;
import com.shardingsearchdemo.db.utils.SpringUtils;
import com.zaxxer.hikari.HikariDataSource;

import javax.sql.DataSource;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author patrick_peng
 * @description 动态数据源配置
 * @date 2023-01-09 23:23
 **/
public class DynamicDataSourceConfig {

    private Map<String, DataSource> dataSourceMap = new ConcurrentHashMap<>();

    private static final String DRIVER_CLASS_NAME = "com.mysql.cj.jdbc.Driver";

    public Map<String, DataSource> getDataSources(){
        setDataSources();
        return dataSourceMap;
    }

    private void setDataSources(){
        RefreshProperties properties = SpringUtils.getBean(RefreshProperties.class);
        List<DataSourceForm> sources = properties.getDataSources();
        if (Boolean.TRUE.equals(EmptyUtils.isNotNull(sources))) {
            for (DataSourceForm source : sources) {
                HikariDataSource dataSource = new HikariDataSource();
                dataSource.setDriverClassName(DRIVER_CLASS_NAME);
                dataSource.setJdbcUrl(source.getJdbcUrl());
                dataSource.setUsername(source.getUsername());
                dataSource.setPassword(source.getPassword());
                dataSourceMap.put(source.getName(), dataSource);
            }
        }
    }
}
