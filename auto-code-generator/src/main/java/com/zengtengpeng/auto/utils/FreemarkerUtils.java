package com.zengtengpeng.auto.utils;

import com.zengtengpeng.auto.AdminStartCode;
import com.zengtengpeng.auto.config.AdminGlobalConfig;
import com.zengtengpeng.autoCode.config.AutoCodeConfig;
import com.zengtengpeng.autoCode.config.GlobalConfig;
import com.zengtengpeng.jdbc.bean.Bean;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * freemarker 模板引擎工具类
 */
public class FreemarkerUtils {

    static Logger logger = LoggerFactory.getLogger(FreemarkerUtils.class);

  static Configuration cfg = new Configuration(Configuration.DEFAULT_INCOMPATIBLE_IMPROVEMENTS);
    static {
        cfg.setClassLoaderForTemplateLoading(FreemarkerUtils.class.getClassLoader(),"/ftl");
    }

    /**
     * 创建页面
     * @param ftlName
     * @param AutoCodeConfig
     */
    public static void createPageFile(String ftlName, AutoCodeConfig AutoCodeConfig) {
        FileWriter writer = null;
        AdminGlobalConfig globalConfig = (AdminGlobalConfig) AutoCodeConfig.getGlobalConfig();
        try {
            Template template = cfg.getTemplate(ftlName +".ftl");
            Bean bean = AutoCodeConfig.getBean();
            File file = new File(globalConfig.getParentPathResources(), globalConfig.getThymeleafPath() +"/"+ bean.getMobelName()+"/");
            if(!file.exists()){
                file.mkdirs();
            }
            file = new File(file.getPath(), bean.getDataName() + "_" + ftlName.split("_")[0] + ".html");

            if(!globalConfig.getCover() && file.exists()){
                logger.info("{}已经存在,conver为{},不再重复生成",file.getAbsolutePath(),globalConfig.getCover());
                return;
            }
            writer = new FileWriter(file);
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
