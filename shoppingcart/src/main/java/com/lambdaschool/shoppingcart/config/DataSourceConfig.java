package com.lambdaschool.shoppingcart.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfig {
    @Value("${local.db.run:H2}")
    private String dbValue;

    @Value("${spring.datasource.url:}")
    private String dbURL;

    @Bean
    public DataSource dataSource(){
        if(dbValue.equalsIgnoreCase("POSTGRESQL")){
            HikariConfig config = new HikariConfig();
            config.setDriverClassName("org.postgresql.Driver");
            config.setJdbcUrl(dbURL);
            return new HikariDataSource(config);
        }else{
            return DataSourceBuilder.create()
                    .username("sa")
                    .password("")
                    .url("jdbc:h2:mem:testdb")
                    .driverClassName("org.h2.Driver")
                    .build();
        }
    }

}
