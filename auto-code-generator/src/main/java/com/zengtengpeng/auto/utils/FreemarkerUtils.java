package com.zengtengpeng.auto.utils;

import com.zengtengpeng.auto.AdminStartCode;
import com.zengtengpeng.auto.config.AdminAutoCodeConfig;
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
     * @param adminAutoCodeConfig
     */
    public static void createPageFile(String ftlName, AdminAutoCodeConfig adminAutoCodeConfig) {
        FileWriter writer = null;
        GlobalConfig globalConfig = adminAutoCodeConfig.getGlobalConfig();
        try {
            Template template = cfg.getTemplate(ftlName +".ftl");
            Bean bean = adminAutoCodeConfig.getBean();
            File file = new File(globalConfig.getParentPathResources(), adminAutoCodeConfig.getThymeleafPath() +"/"+ bean.getMobelName()+"/");
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
