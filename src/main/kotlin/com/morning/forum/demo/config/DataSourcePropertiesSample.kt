package com.morning.forum.demo.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.stereotype.Component

/**
 * データベースの設定を読み込むためのクラス．
 *　設定自体はapplication.yamlに記述されている
 */
@Component
@ConfigurationProperties(prefix="spring.datasource.sample")
class DatasourceSampleProperties {
    private lateinit var driverClassName: String
    private lateinit var url: String
    private lateinit var username: String
    private lateinit var password: String

    fun getDriverClassName() : String {
        return driverClassName
    }

    fun setDriverClassName(driverClassName : String) {
        this.driverClassName = driverClassName
    }

    fun getUrl() : String {
        return url
    }

    fun setUrl(url : String) {
        this.url = url
    }

    fun getUsername() :String {
        return username
    }

    fun setUsername(username : String) {
        this.username = username
    }

    fun getPassword() : String {
        return password
    }
    fun setPassword(password : String) {
        this.password = password
    }
}