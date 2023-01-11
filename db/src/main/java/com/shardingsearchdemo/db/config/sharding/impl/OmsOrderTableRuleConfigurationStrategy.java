package com.shardingsearchdemo.db.config.sharding.impl;

import com.baomidou.mybatisplus.annotation.TableName;
import com.shardingsearchdemo.db.config.sharding.ITableRuleConfigurationStrategy;
import com.shardingsearchdemo.db.entity.OmsOrder;
import lombok.extern.slf4j.Slf4j;
import org.apache.shardingsphere.infra.config.algorithm.AlgorithmConfiguration;
import org.apache.shardingsphere.sharding.algorithm.sharding.mod.HashModShardingAlgorithm;
import org.apache.shardingsphere.sharding.api.config.ShardingRuleConfiguration;
import org.apache.shardingsphere.sharding.api.config.rule.ShardingTableRuleConfiguration;
import org.apache.shardingsphere.sharding.api.config.strategy.sharding.StandardShardingStrategyConfiguration;
import org.springframework.stereotype.Service;

import java.util.Properties;

/**
 * @author patrick_peng
 * @description 订单表分片规则策略实现类
 * @date 2023-01-10 15:15
 **/
@Service(value = "OmsOrderTableRule")
@Slf4j
public class OmsOrderTableRuleConfigurationStrategy implements ITableRuleConfigurationStrategy {
    /**
     * 获取分片规则
     *
     * @return
     */
    @Override
    public ShardingTableRuleConfiguration getTableRuleConfiguration(ShardingRuleConfiguration result) {
        String tableName = getTableName();
        ShardingTableRuleConfiguration configuration = new ShardingTableRuleConfiguration(tableName,"sharding-search-demo-${0..1}.oms_order_${0..3}");
        //分片算法策略
        StandardShardingStrategyConfiguration tableShardingStrategy = new StandardShardingStrategyConfiguration("order_id", this.getClass().getSimpleName());
        Properties properties = new Properties();
        //配置分表数量
        properties.put("sharding-count", "4");
        result.getShardingAlgorithms().put(this.getClass().getSimpleName(), new AlgorithmConfiguration(new HashModShardingAlgorithm().getType(), properties));
        configuration.setTableShardingStrategy(tableShardingStrategy);
        return configuration;
    }

    /**
     * 表名称
     *
     * @return
     */
    @Override
    public String getTableName() {
        return OmsOrder.class.getAnnotation(TableName.class).value();
    }
}
