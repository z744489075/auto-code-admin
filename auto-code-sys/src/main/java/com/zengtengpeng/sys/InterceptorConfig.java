package com.zengtengpeng.sys;

import com.zengtengpeng.sys.interceptor.UserInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 拦截器注入
 */
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    @Bean
    public UserInterceptor userInterceptor(){
        return new UserInterceptor();
    }
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(userInterceptor()).addPathPatterns("/**");
    }
}