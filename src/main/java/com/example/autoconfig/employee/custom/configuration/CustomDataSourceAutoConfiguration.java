package com.example.autoconfig.employee.custom.configuration;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;

import javax.sql.DataSource;

@AutoConfiguration
@ConditionalOnClass(DataSource.class)
@ConditionalOnProperty(
        prefix = "custom.datasource",
        name = "enabled",
        havingValue = "true"
)
public class CustomDataSourceAutoConfiguration {

    @Value("${custom.datasource.url}")
    private String url;

    @Value("${custom.datasource.username}")
    private String username;

    @Value("${custom.datasource.password}")
    private String password;

    @Bean
    @ConditionalOnMissingBean(DataSource.class)
    public DataSource dataSource() {
        System.out.println("Creating Custom DataSource...");
        HikariDataSource ds = new HikariDataSource();

        ds.setJdbcUrl(url);
        ds.setUsername(username);
        ds.setPassword(password);

        return ds;
    }
}
