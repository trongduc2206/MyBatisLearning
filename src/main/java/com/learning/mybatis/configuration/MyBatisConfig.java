package com.learning.mybatis.configuration;

import com.learning.mybatis.interceptor.BaseEntityInterceptor;
import com.learning.mybatis.typehandler.UuidTypeHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyBatisConfig {
    @Bean
    public BaseEntityInterceptor baseEntityInterceptor() {
        return new BaseEntityInterceptor();
    }

//    @Bean
//    public UuidTypeHandler uuidTypeHandler() {
//        return new UuidTypeHandler();
//    }

}
