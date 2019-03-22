package com.zengtengpeng.extend.base;

import com.zengtengpeng.autoCode.config.AutoCodeConfig;
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
     * @param AutoCodeConfig
     */
    default void build(AutoCodeConfig AutoCodeConfig){
        foreignGotoDetail(AutoCodeConfig);
        foreignAuth(AutoCodeConfig);
        primaryAuth(AutoCodeConfig);
    }

    /**
     * 获取文件路径
     * @param globalConfig
     * @param relationTable
     * @return
     */
    default String getFilePath(GlobalConfig globalConfig,RelationTable relationTable){
        return globalConfig.getParentPathJavaSource()+"/"+relationTable.getExistParentPackage().replace(".","/")+"/"+globalConfig.getPackageController()+"/"+relationTable.getBeanName()+globalConfig.getPackageControllerUp()+".java";
    }
    /**
     * 修改主表权限
     */
    default void primaryAuth(AutoCodeConfig AutoCodeConfig){
        GlobalConfig globalConfig = AutoCodeConfig.getGlobalConfig();
        RelationConfig relationConfig = globalConfig.getRelationConfig();
        RelationTable primary = relationConfig.getPrimary();
        String filePath = getFilePath(globalConfig, primary);
        File file=new File(filePath);
        if (!file.exists()){
            logger.info("{}不存在,忽略修改",filePath);
            return;
        }
        FileWriter fileWriter=null;

        FileReader fileReader=null;
        BufferedReader bufferedReader=null;
        try {
            StringBuffer content=new StringBuffer();
            fileReader=new FileReader(file);
            bufferedReader=new BufferedReader(fileReader);
            String s;
            String selectAuth=String.format("@Auth(\"%s/selectAllByPaging\")",primary.getBeanNameLower());
            String deleteAuth=String.format("@Auth(\"%s/deleteByPrimaryKey\")",primary.getBeanNameLower());
            String before="";
            while ((s=bufferedReader.readLine())!=null){
                if(s.contains("@RequestMapping")&&s.contains("select")&&!before.contains(selectAuth)){
                    content.append("\t"+selectAuth+"\n");
                }
                if(s.contains("@RequestMapping")&&s.contains("delete")&&!before.contains(deleteAuth)){
                    content.append("\t"+deleteAuth+"\n");
                }
                before=s;
                content.append(s+"\n");
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
    /**
     * 修改外表权限
     */
    default void foreignAuth(AutoCodeConfig AutoCodeConfig){
        GlobalConfig globalConfig = AutoCodeConfig.getGlobalConfig();
        RelationConfig relationConfig = globalConfig.getRelationConfig();
        RelationTable foreign = relationConfig.getForeign();
        RelationTable primary = relationConfig.getPrimary();
        String filePath = getFilePath(globalConfig, foreign);
        File file=new File(filePath);
        if (!file.exists()){
            logger.info("{}不存在,忽略修改",filePath);
            return;
        }
        FileWriter fileWriter=null;

        FileReader fileReader=null;
        BufferedReader bufferedReader=null;
        try {
            StringBuffer content=new StringBuffer();
            fileReader=new FileReader(file);
            bufferedReader=new BufferedReader(fileReader);
            String s;
            String selectAuth=String.format("@Auth(\"%s/selectAllByPaging\")",foreign.getBeanNameLower());
            String deleteAuth=String.format("@Auth(\"%s/deleteByPrimaryKey\")",foreign.getBeanNameLower());
            String before="";
            while ((s=bufferedReader.readLine())!=null){
                if(s.contains("@RequestMapping")&&s.contains("select")&&!before.contains(selectAuth)){
                    content.append("\t"+selectAuth+"\n");
                }
                if(s.contains("@RequestMapping")&&s.contains("delete")&&!before.contains(deleteAuth)){
                    content.append("\t"+deleteAuth+"\n");
                }
                before=s;
                content.append(s+"\n");
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
    /**
     * 修改外表的 gotoDetail 方法
     * @param AutoCodeConfig
     */
    default void foreignGotoDetail(AutoCodeConfig AutoCodeConfig){
        GlobalConfig globalConfig = AutoCodeConfig.getGlobalConfig();
        RelationConfig relationConfig = globalConfig.getRelationConfig();
        RelationTable foreign = relationConfig.getForeign();
        RelationTable primary = relationConfig.getPrimary();
        String filePath = getFilePath(globalConfig, foreign);
        File file=new File(filePath);
        if (!file.exists()){
            logger.info("{}不存在,忽略修改",filePath);
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
