package com.shardingsearchdemo.db.config;

import com.shardingsearchdemo.db.DbApplication;
import com.shardingsearchdemo.db.config.entity.DataSourceForm;
import com.shardingsearchdemo.db.utils.SpringUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * @author patrick_peng
 * @description
 * @date 2023-01-09 23:43
 **/
@SpringBootTest(classes = DbApplication.class)
@Slf4j
public class RefreshPropertiesTest {

    @Test
    public void getDataSourcesShardingConfig() {
        RefreshProperties refreshProperties = SpringUtils.getBean(RefreshProperties.class);
        List<DataSourceForm> config = refreshProperties.getDataSources();
        log.info(config.toString());
    }
}