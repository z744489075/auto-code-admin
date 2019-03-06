package com.zengtengpeng.auto;

import com.zengtengpeng.auto.config.AdminAutoCodeConfig;
import com.zengtengpeng.auto.create.AdminBuildController;
import com.zengtengpeng.auto.utils.AuthUtils;
import com.zengtengpeng.auto.utils.FreemarkerUtils;
import com.zengtengpeng.autoCode.StartCode;
import com.zengtengpeng.autoCode.config.AutoCodeConfig;
import com.zengtengpeng.autoCode.create.BuildController;
import org.yaml.snakeyaml.Yaml;

/**
 * 重新构建
 */
public class AdminStartCode implements StartCode {

    /**
     * 解析yaml文件
     */
    public static AdminAutoCodeConfig saxYaml() {
        return AdminStartCode.saxYaml("auto-code.yaml");
    }

    /**
     * 解析yaml文件
     * @param fileName 文件名称
     */
     public static  AdminAutoCodeConfig saxYaml(String fileName) {
        Yaml yaml = new Yaml();
        return yaml.loadAs(StartCode.class.getClassLoader().getResourceAsStream(fileName), AdminAutoCodeConfig.class);
    }
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

        //生成页面
        AdminAutoCodeConfig adminAutoCodeConfig = (AdminAutoCodeConfig) autoCodeConfig;
        FreemarkerUtils.createPageFile("list_page", adminAutoCodeConfig);
        FreemarkerUtils.createPageFile("detail_page", adminAutoCodeConfig);

        //生成权限
        AuthUtils.addAuth(adminAutoCodeConfig);
    }
}
