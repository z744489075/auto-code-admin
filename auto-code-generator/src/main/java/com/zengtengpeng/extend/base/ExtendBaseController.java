package com.zengtengpeng.extend.base;

import com.zengtengpeng.auto.config.AdminAutoCodeConfig;
import com.zengtengpeng.autoCode.config.GlobalConfig;
import com.zengtengpeng.autoCode.utils.MyStringUtils;
import com.zengtengpeng.relation.bean.RelationTable;
import com.zengtengpeng.relation.config.RelationConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

/**
 * 扩展 controller
 */
public interface ExtendBaseController {

    Logger logger = LoggerFactory.getLogger(ExtendBaseListPage.class);

    /**
     * 构建
     * @param adminAutoCodeConfig
     */
    default void build(AdminAutoCodeConfig adminAutoCodeConfig){
        foreignGotoDetail(adminAutoCodeConfig);
    }
    /**
     * 修改外表的 gotoDetail 方法
     * @param adminAutoCodeConfig
     */
    default void foreignGotoDetail(AdminAutoCodeConfig adminAutoCodeConfig){
        GlobalConfig globalConfig = adminAutoCodeConfig.getGlobalConfig();
        RelationConfig relationConfig = globalConfig.getRelationConfig();
        RelationTable foreign = relationConfig.getForeign();
        RelationTable primary = relationConfig.getPrimary();
        String path=globalConfig.getParentPathJavaSource()+"/"+foreign.getExistParentPackage().replace(".","/")+"/"+globalConfig.getPackageController()+"/"+foreign.getBeanName()+globalConfig.getPackageControllerUp()+".java";
        File file=new File(path);
        if (!file.exists()){
            logger.info("{}不存在,忽略修改",path);
            return;
        }
        FileWriter fileWriter=null;

        FileReader fileReader=null;
        BufferedReader bufferedReader=null;
        try {
            StringBuffer content=new StringBuffer();
            StringBuffer oldContent=new StringBuffer();
            fileReader=new FileReader(file);
            bufferedReader=new BufferedReader(fileReader);
            bufferedReader.mark((int) file.length());
            String s;
            while ((s=bufferedReader.readLine())!=null){
                oldContent.append(s+"\n");
            }
            bufferedReader.reset();
            while ((s=bufferedReader.readLine())!=null){

                content.append(s+"\n");
                //添加外表列
                if(s.contains("gotoDetail(")){
                    StringBuffer text=new StringBuffer();
                    MyStringUtils.append(text,"List<%s> data = %s%s.selectAll(null);",2,primary.getBeanName(),primary.getBeanNameLower(),globalConfig.getPackageServiceUp());
                    MyStringUtils.append(text,"request.setAttribute(\"goto_detail_%s\",data);",2,primary.getDataName());
                    if(oldContent.indexOf(text.toString())<0){
                        content.append(text.toString()+"\n");
                    }else {
                        logger.error("重复代码过滤->\n{}",text);
                    }
                }
            }
            fileWriter=new FileWriter(file);
            fileWriter.write(content.toString());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }finally {
            try {
                if (fileWriter != null) {
                    fileWriter.close();
                }
                if (fileReader != null) {
                    fileReader.close();
                }
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

}
