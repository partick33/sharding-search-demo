package com.demo.shardingsearchdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.demo.shardingsearchdemo", "generator"})
public class ShardingSearchDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShardingSearchDemoApplication.class, args);
    }

}
