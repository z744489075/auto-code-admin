package com.zengtengpeng.auto.config;

import com.zengtengpeng.autoCode.config.AutoCodeConfig;

/**
 * admin 全局配置类
 */
public class AdminAutoCodeConfig extends AutoCodeConfig {

    //thymeleaf 放置页面的文件夹
    private String thymeleafPath="templates";

    //添加权限到表的父id.如果为空则需要手动添加权限
    private String authParentId;

    public String getThymeleafPath() {
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
