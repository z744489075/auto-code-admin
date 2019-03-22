package com.zengtengpeng.extend.build.manyToMany;

import com.zengtengpeng.autoCode.config.AutoCodeConfig;
import com.zengtengpeng.autoCode.config.GlobalConfig;
import com.zengtengpeng.autoCode.utils.MyStringUtils;
import com.zengtengpeng.extend.base.ExtendBaseDetailPage;
import com.zengtengpeng.relation.bean.RelationTable;
import com.zengtengpeng.relation.config.RelationConfig;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

/**
 * 构建一对一追加列表页方法
 */
public class ExtendManyToManyDetailPage implements ExtendBaseDetailPage {

    /**
     * 构建
     * @param AutoCodeConfig
     */
    public void build(AutoCodeConfig AutoCodeConfig){
        foreignDetailPage(AutoCodeConfig);
        primaryDetailPage(AutoCodeConfig);
    }
    /**
     * 修改外表的 详情页面
     * @param AutoCodeConfig
     */
    public void foreignDetailPage(AutoCodeConfig AutoCodeConfig){
        GlobalConfig globalConfig = AutoCodeConfig.getGlobalConfig();
        RelationConfig relationConfig = globalConfig.getRelationConfig();
        RelationTable foreign = relationConfig.getForeign();
        RelationTable primary = relationConfig.getPrimary();
        RelationTable thirdparty = relationConfig.getThirdparty();
        File file=new File(getFilePath(AutoCodeConfig,foreign));
        if (!file.exists()){
            logger.info("{}不存在,忽略修改",file.getAbsolutePath());
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
                if(s.contains("<form class=\"layui-form")){
                    StringBuffer text=new StringBuffer();
                    MyStringUtils.append(text,"<div class=\"layui-form-item\">",4);
                    MyStringUtils.append(text,"<label class=\"layui-form-label\">%s</label>",5,primary.getRemark());
                    MyStringUtils.append(text,"<div class=\"layui-input-block\">",5);
                    MyStringUtils.append(text,"<select name=\"%s\" id=\"%s\" xm-select=\"select13\"  xm-select-search=\"\" >",6,
                            thirdparty.getPrimaryKeyUp(false),thirdparty.getPrimaryKeyUp(false));
                    MyStringUtils.append(text,"<option  th:each=\"rowdata:${goto_detail_%s}\" th:value=\"${rowdata.%s}\" th:selected=\"${sId!=null and #lists.contains(sId,rowdata.%s+'')}\"  th:text=\"${rowdata.%s}\"></option>",7,
                            primary.getDataName(),primary.getPrimaryKeyUp(false),primary.getPrimaryKeyUp(false),primary.getPrimaryKeyUp(false));
                    MyStringUtils.append(text,"</select>",6);
                    MyStringUtils.append(text,"</div>",5);
                    MyStringUtils.append(text,"</div>",4);
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
    /**
     * 修改主表的 详情页面
     * @param AutoCodeConfig
     */
    public void primaryDetailPage(AutoCodeConfig AutoCodeConfig){
        GlobalConfig globalConfig = AutoCodeConfig.getGlobalConfig();
        RelationConfig relationConfig = globalConfig.getRelationConfig();
        RelationTable foreign = relationConfig.getForeign();
        RelationTable primary = relationConfig.getPrimary();
        RelationTable thirdparty = relationConfig.getThirdparty();
        File file=new File(getFilePath(AutoCodeConfig,primary));
        if (!file.exists()){
            logger.info("{}不存在,忽略修改",file.getAbsolutePath());
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
                //添加主表列
                if(s.contains("<form class=\"layui-form")){
                    StringBuffer text=new StringBuffer();
                    MyStringUtils.append(text,"<div class=\"layui-form-item\">",4);
                    MyStringUtils.append(text,"<label class=\"layui-form-label\">%s</label>",5,foreign.getRemark());
                    MyStringUtils.append(text,"<div class=\"layui-input-block\">",5);
                    MyStringUtils.append(text,"<select name=\"%s\" id=\"%s\" xm-select=\"select13\"  xm-select-search=\"\" >",6,
                            thirdparty.getForeignKeyUp(false),thirdparty.getForeignKeyUp(false));
                    MyStringUtils.append(text,"<option  th:each=\"rowdata:${goto_detail_%s}\" th:value=\"${rowdata.%s}\" th:selected=\"${sId!=null and #lists.contains(sId,rowdata.%s+'')}\"  th:text=\"${rowdata.%s}\"></option>",7,
                            foreign.getDataName(),foreign.getForeignKeyUp(false),foreign.getForeignKeyUp(false),foreign.getForeignKeyUp(false));
                    MyStringUtils.append(text,"</select>",6);
                    MyStringUtils.append(text,"</div>",5);
                    MyStringUtils.append(text,"</div>",4);
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
