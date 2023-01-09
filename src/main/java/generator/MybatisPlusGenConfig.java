package generator;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.Collections;

/**
 * @author patrick_peng
 * @description sql代码生成器配置
 * @date 2022-11-27 21:18
 **/
@Slf4j
@Component
@RefreshScope
public class MybatisPlusGenConfig {
    @Value("${spring.datasource.url}")
    private String url;

    @Value("${spring.datasource.username}")
    private String username;

    @Value("${spring.datasource.password}")
    private String password;


    public void generate(String tableNames){
        log.info(url);
        //数据库配置
        DataSourceConfig dataSourceConfig = new DataSourceConfig.Builder(url, username, password).build();
        //全局配置
        GlobalConfig globalConfig = new GlobalConfig.Builder()
                .author("partick_peng")
                .commentDate("yyyy-MM-dd")
                .outputDir(System.getProperty("user.dir") + "/src/main/java/generator")
                .build();
        //包配置
        PackageConfig packageConfig = new PackageConfig.Builder()
                .parent("generator")
                .moduleName("sys")
                .entity("entity")
                .service("service")
                .serviceImpl("service.impl")
                .mapper("mapper")
                .xml("mapper.xml")
                .controller("controller")
                .build();
        //策略配置
        StrategyConfig strategyConfig = new StrategyConfig.Builder()
                .enableCapitalMode()
                .enableSkipView()
                .disableSqlFilter()
                .addInclude(ObjectUtils.isEmpty(tableNames) ? Collections.emptyList() : Arrays.asList(tableNames.split(",")))
                .build();

        //Mapper
        StrategyConfig mapperConfig = new StrategyConfig.Builder().mapperBuilder()
                .enableBaseResultMap()
                .enableBaseColumnList().build();

        //Entity
        StrategyConfig entityConfig = new StrategyConfig.Builder().entityBuilder()
                .enableLombok()
                .enableTableFieldAnnotation()
                .idType(IdType.AUTO).build();

        AutoGenerator autoGenerator = new AutoGenerator(dataSourceConfig);
        autoGenerator.global(globalConfig);
        autoGenerator.packageInfo(packageConfig);
        autoGenerator.strategy(strategyConfig);
        autoGenerator.strategy(mapperConfig);
        autoGenerator.strategy(entityConfig);
        autoGenerator.execute(new FreemarkerTemplateEngine());
    }
}
