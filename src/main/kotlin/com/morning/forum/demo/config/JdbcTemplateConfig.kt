package com.morning.forum.demo.config

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.jdbc.DataSourceBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Primary
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Component
import javax.sql.DataSource

@Component
class JdbcTemplateConfig {
    @Autowired
    private lateinit var dataSourceConfig: DatasourceSampleProperties

    @Bean
    @Primary
    fun createDataSource(): DataSource {
        return DataSourceBuilder
            .create()
            .driverClassName(dataSourceConfig.getDriverClassName())
            .url(dataSourceConfig.getUrl())
            .username(dataSourceConfig.getUsername())
            .password(dataSourceConfig.getPassword())
            .build()
    }

    @Bean
    @Primary
    fun createJdbcTemplate(dataSource: DataSource): JdbcTemplate {
        return JdbcTemplate(dataSource)
    }
}