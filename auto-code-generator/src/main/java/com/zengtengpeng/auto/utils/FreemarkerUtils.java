package com.zengtengpeng.auto.utils;

import com.zengtengpeng.auto.AdminStartCode;
import com.zengtengpeng.autoCode.config.AutoCodeConfig;
import com.zengtengpeng.autoCode.config.GlobalConfig;
import com.zengtengpeng.jdbc.bean.Bean;
import freemarker.template.Configuration;
import freemarker.template.Template;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * freemarker 模板引擎工具类
 */
public class FreemarkerUtils {


  static Configuration cfg = new Configuration(Configuration.DEFAULT_INCOMPATIBLE_IMPROVEMENTS);
    static {
        cfg.setClassLoaderForTemplateLoading(FreemarkerUtils.class.getClassLoader(),"/ftl");
    }

    /**
     * 创建页面
     * @param ftlName
     * @param autoCodeConfig
     */
    public static void createPageFile(String ftlName, AutoCodeConfig autoCodeConfig) {
        FileWriter writer = null;
        GlobalConfig globalConfig = autoCodeConfig.getGlobalConfig();

        try {
            Template template = cfg.getTemplate(ftlName +".ftl");
            Bean bean = autoCodeConfig.getBean();
            File file = new File(globalConfig.getParentPathResources(), AdminStartCode.templatesPath +"/"+ bean.getMobelName()+"/");
            if(!file.exists()){
                file.mkdirs();
            }
            writer = new FileWriter(new File(file.getPath(),bean.getDataName()+"_"+ftlName.split("_")[0] + ".html"));
            template.process(bean, writer);
            writer.flush();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

            }
        }
    }
}
