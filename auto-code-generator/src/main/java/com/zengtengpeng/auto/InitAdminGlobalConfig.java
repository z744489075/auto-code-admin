package com.zengtengpeng.auto;

import com.zengtengpeng.AutoCodeProperties;
import com.zengtengpeng.auto.config.AdminGlobalConfig;
import com.zengtengpeng.autoCode.config.AutoCodeConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

@Configuration
@EnableConfigurationProperties(value = AdminGlobalConfig.class)
public class InitAdminGlobalConfig {

    @Autowired
    private AdminGlobalConfig adminGlobalConfig;
    @Bean
    public AutoCodeConfig autoCodeConfig() {
        AutoCodeConfig autoCodeConfig=new AutoCodeConfig();
        autoCodeConfig.setGlobalConfig(adminGlobalConfig);
        return autoCodeConfig;
    }
}
