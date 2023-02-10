package com.shardingsearchdemo.db.config.sharding;

import com.shardingsearchdemo.db.config.RefreshProperties;
import com.shardingsearchdemo.db.utils.EmptyUtils;
import com.shardingsearchdemo.db.utils.SpringUtils;
import org.apache.shardingsphere.driver.api.ShardingSphereDataSourceFactory;
import org.apache.shardingsphere.infra.config.algorithm.AlgorithmConfiguration;
import org.apache.shardingsphere.infra.config.mode.ModeConfiguration;
import org.apache.shardingsphere.sharding.algorithm.sharding.mod.HashModShardingAlgorithm;
import org.apache.shardingsphere.sharding.api.config.ShardingRuleConfiguration;
import org.apache.shardingsphere.sharding.api.config.rule.ShardingTableRuleConfiguration;
import org.apache.shardingsphere.sharding.api.config.strategy.sharding.StandardShardingStrategyConfiguration;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.*;

/**
 * @author patrick_peng
 * @description 分表分库数据源配置（含分库分表策略）
 * @date 2023-01-10 16:54
 **/
public class DemoDataSourceConfig{

    public static DataSource getDataSource() throws SQLException {
        //逻辑库名称
        RefreshProperties properties = SpringUtils.getBean(RefreshProperties.class);
        String databaseName = properties.getDatabaseName();
        //模式配置
        DemoModeConfiguration demoModeConfiguration = SpringUtils.getBean(DemoModeConfiguration.class);
        ModeConfiguration modeConfiguration = demoModeConfiguration.getDemoModeConfiguration();
        //真实数据源
        DynamicDataSourceConfig sourceConfig = SpringUtils.getBean(DynamicDataSourceConfig.class);
        Map<String, DataSource> dataSources = sourceConfig.getDataSources();
        Properties prop = new Properties();
        //开启sql打印
        prop.put("sql-show", true);
        //开启元数据检查，默认关闭，建议开启，可以检查自己的表达式是否跟表名对应得上
        prop.put("check-table-metadata-enabled", true);
        //简化sql
        //prop.put("sql-simple",true);
        return ShardingSphereDataSourceFactory.createDataSource(databaseName, modeConfiguration, dataSources, Collections.singleton(createShardingRuleConfiguration()), prop);
    }

    private static ShardingRuleConfiguration createShardingRuleConfiguration() {
        ShardingRuleConfiguration result = new ShardingRuleConfiguration();
        RefreshProperties properties = SpringUtils.getBean(RefreshProperties.class);
        String databaseName = properties.getDatabaseName();
        Map<String, ITableRuleConfigurationStrategy> ruleConfigurationStrategyMap = TableRuleConfigurationFactory.get();
        StandardShardingStrategyConfiguration dataBaseStrategy = new StandardShardingStrategyConfiguration("user_id", "dataBaseStrategy");
        Properties props = new Properties();
        props.put("sharding-count", "2");
        result.getShardingAlgorithms().put("dataBaseStrategy", new AlgorithmConfiguration(new HashModShardingAlgorithm().getType(), props));
        if (Boolean.TRUE.equals(EmptyUtils.isNotNull(ruleConfigurationStrategyMap))) {
            List<ITableRuleConfigurationStrategy> list = new ArrayList<>(ruleConfigurationStrategyMap.values());
            for (ITableRuleConfigurationStrategy strategy : list) {
                ShardingTableRuleConfiguration configuration = strategy.getTableRuleConfiguration(result);
                result.getTables().add(configuration);
                configuration.setDatabaseShardingStrategy(dataBaseStrategy);
            }
        }
        return result;
    }

}
