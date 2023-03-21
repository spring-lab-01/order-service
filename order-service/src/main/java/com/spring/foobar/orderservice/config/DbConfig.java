package com.spring.foobar.orderservice.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

@Configuration
public class DbConfig {

    @Bean
    @Primary
    @ConfigurationProperties(prefix="spring.datasource.write")
    public DataSource writeDataSource(){
        return DataSourceBuilder.create().build();
    }

    @Bean
    public JdbcTemplate writeJdbcTemplate(@Qualifier("writeDataSource") DataSource writeDataSource){
        return new JdbcTemplate(writeDataSource);
    }

    @Bean
    @ConfigurationProperties(prefix="spring.datasource.read")
    public DataSource readDataSource(){
        return DataSourceBuilder.create().build();
    }

    @Bean
    public JdbcTemplate readJdbcTemplate(@Qualifier("readDataSource") DataSource readDataSource){
        return new JdbcTemplate(readDataSource);
    }

}
