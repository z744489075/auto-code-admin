package com.zengtengpeng.auto;

import com.zengtengpeng.auto.create.AdminBuildController;
import com.zengtengpeng.auto.utils.FreemarkerUtils;
import com.zengtengpeng.autoCode.StartCode;
import com.zengtengpeng.autoCode.config.AutoCodeConfig;
import com.zengtengpeng.autoCode.create.BuildController;

/**
 * 重新构建
 */
public class AdminStartCode implements StartCode {

    /**
     * 生成页面存放的目录
     */
    public static String templatesPath="templates";
    /**
     * 重写构建controller
     * @return
     */
    @Override
    public BuildController BuildController() {
        return new AdminBuildController();
    }

    /**
     * 自定义 生成接口结束后admin还需要生成页面
     * @param autoCodeConfig
     */
    @Override
    public void custom(AutoCodeConfig autoCodeConfig) {

        //
        FreemarkerUtils.createPageFile("list_page",autoCodeConfig);
        FreemarkerUtils.createPageFile("detail_page",autoCodeConfig);
    }
}
