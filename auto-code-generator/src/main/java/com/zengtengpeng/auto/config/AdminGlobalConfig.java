package com.zengtengpeng.auto.config;


import com.zengtengpeng.autoCode.config.GlobalConfig;
import com.zengtengpeng.autoCode.utils.MyStringUtils;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@ConfigurationProperties(prefix = "auto-code")
public class AdminGlobalConfig extends GlobalConfig  {
    //thymeleaf 放置页面的文件夹
    private String thymeleafPath="templates";

    //添加权限到表的父id.如果为空则需要手动添加权限
    private String authParentId;

    public String getThymeleafPath() {
        if(MyStringUtils.isEmpty(thymeleafPath)){
            return "templates";
        }
        return thymeleafPath;
    }

    public void setThymeleafPath(String thymeleafPath) {
        this.thymeleafPath = thymeleafPath;
    }

    public String getAuthParentId() {
        return authParentId;
    }

    public void setAuthParentId(String authParentId) {
        this.authParentId = authParentId;
    }


}
