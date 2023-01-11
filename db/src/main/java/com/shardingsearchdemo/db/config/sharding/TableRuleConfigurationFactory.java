package com.shardingsearchdemo.db.config.sharding;

import com.shardingsearchdemo.db.utils.EmptyUtils;
import com.shardingsearchdemo.db.utils.SpringUtils;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author patrick_peng
 * @description 表分片规则单例工厂
 * @date 2023-01-10 14:45
 **/
public class TableRuleConfigurationFactory {

    private Map<String, ITableRuleConfigurationStrategy> map = new ConcurrentHashMap<>();

    private static class Singleton{
        private static final TableRuleConfigurationFactory INSTANCE = new TableRuleConfigurationFactory();
    }

    private TableRuleConfigurationFactory(){
        Map<String, ITableRuleConfigurationStrategy> ruleMap = SpringUtils.getBeanByType(ITableRuleConfigurationStrategy.class);
        if (EmptyUtils.isNotNull(ruleMap)){
            for (Map.Entry<String, ITableRuleConfigurationStrategy> entry : ruleMap.entrySet()) {
                ITableRuleConfigurationStrategy strategy = entry.getValue();
                map.put(strategy.getTableName(), strategy);
            }
        }
    }

    public static Map<String, ITableRuleConfigurationStrategy> get(){
        return Singleton.INSTANCE.map;
    }

    public static ITableRuleConfigurationStrategy get(String name){
        return Singleton.INSTANCE.map.get(name);
    }
}
