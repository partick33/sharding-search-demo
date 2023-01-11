package com.shardingsearchdemo.db.config.sharding;

import com.shardingsearchdemo.db.config.RefreshProperties;
import com.shardingsearchdemo.db.constant.BasConstant;
import com.shardingsearchdemo.db.utils.SpringUtils;
import org.apache.shardingsphere.infra.config.mode.ModeConfiguration;
import org.apache.shardingsphere.mode.repository.standalone.StandalonePersistRepositoryConfiguration;

import java.util.Properties;

/**
 * @author patrick_peng
 * @description 模式配置
 * @date 2023-01-10 12:50
 **/
public class DemoModeConfiguration {

    public ModeConfiguration  getDemoModeConfiguration(){
        RefreshProperties properties = SpringUtils.getBean(RefreshProperties.class);
        String modeType = properties.getModeType();
        if (BasConstant.STANDALONE.equals(modeType)){
            return new ModeConfiguration(BasConstant.STANDALONE,new StandalonePersistRepositoryConfiguration("JDBC", new Properties()), true);
        }else{
            //todo 集群模式配置
            return null;
        }
    }
}
