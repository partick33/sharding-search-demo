package com.shardingsearchdemo.db.config.entity;

import lombok.Data;

/**
 * @author patrick_peng
 * @description 数据源配置表单
 * @date 2023-01-09 23:28
 **/
@Data
public class DataSourceForm {
    /**
     * 数据库名称
     */
    private String name;

    /**
     * 路径
     */
    private String jdbcUrl;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;
}
