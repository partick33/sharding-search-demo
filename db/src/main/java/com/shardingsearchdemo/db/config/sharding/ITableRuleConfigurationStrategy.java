package com.shardingsearchdemo.db.config.sharding;

import org.apache.shardingsphere.sharding.api.config.ShardingRuleConfiguration;
import org.apache.shardingsphere.sharding.api.config.rule.ShardingTableRuleConfiguration;

/**
 * @author patrick_peng
 * @description 表分片规则策略接口
 * @date 2023-01-10 14:42
 **/
public interface ITableRuleConfigurationStrategy {

    /**
     * 获取分片规则
     * @return
     */
    ShardingTableRuleConfiguration getTableRuleConfiguration(ShardingRuleConfiguration result);

    /**
     * 表名称
     * @return
     */
    String getTableName();
}
