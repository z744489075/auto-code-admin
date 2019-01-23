package com.zengtengpeng.sys;

import com.zengtengpeng.interceptor.LogInterceptor;
import com.zengtengpeng.interceptor.UserInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 拦截器注入
 */
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    /**
     * 用户权限拦截器
     * @return
     */
    @Bean
    public UserInterceptor userInterceptor(){
        return new UserInterceptor();
    }

    /**
     * 日志拦截器
     * @return
     */
    @Bean
    public LogInterceptor logInterceptor(){
        return new LogInterceptor();
    }


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(userInterceptor()).addPathPatterns("/**");
        registry.addInterceptor(logInterceptor()).addPathPatterns("/**");
    }
}