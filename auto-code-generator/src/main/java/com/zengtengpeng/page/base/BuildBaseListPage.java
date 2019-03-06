package com.zengtengpeng.page.base;

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
 * 多表生成页面追加列表页面代码接口
 */
public interface BuildBaseListPage {

    Logger logger = LoggerFactory.getLogger(BuildBaseListPage.class);

    /**
     * 构建
     * @param adminAutoCodeConfig
     */
    default void build(AdminAutoCodeConfig adminAutoCodeConfig){
        foreignChangeListPage(adminAutoCodeConfig);
        primaryChangeListPage(adminAutoCodeConfig);
    }
    /**
     * 修改外表的 list页面
     * @param adminAutoCodeConfig
     */
    default void foreignChangeListPage(AdminAutoCodeConfig adminAutoCodeConfig){
        GlobalConfig globalConfig = adminAutoCodeConfig.getGlobalConfig();
        RelationConfig relationConfig = globalConfig.getRelationConfig();
        RelationTable foreign = relationConfig.getForeign();
        RelationTable primary = relationConfig.getPrimary();
        String path=globalConfig.getParentPathResources()+"/"+adminAutoCodeConfig.getThymeleafPath()+"/"+foreign.getExistParentPackage_()+"/"+foreign.getDataName()+"_list.html";
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
                //添加外表列
                if(s.contains("toolbar: '#barDemo'")){
                    String format = String.format(",{title: '%s',templet: function(d){\n" +
                                    "                        return '<a class=\"layui-btn layui-btn-xs\" onclick=\"openUrl(\\'%s/gotoList?fkey=%s&fvalue='+d.%s+'\\')\"><i class=\"layui-icon layui-icon-search\"></i>详情</a>';\n" +
                                    "                    }\n" +
                                    "                }", primary.getRemark(), primary.getBeanNameLower(),
                            primary.getPrimaryKeyUp(false), foreign.getForeignKeyUp(false));
                    if(oldContent.indexOf(format)<0){
                        content.append("\t\t\t\t"+format+"\n");
                    }else {
                        logger.error("重复代码过滤->\n{}",format);
                    }
                }
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
     * 修改主表的 list页面
     * @param adminAutoCodeConfig
     */
    default void primaryChangeListPage(AdminAutoCodeConfig adminAutoCodeConfig){
        GlobalConfig globalConfig = adminAutoCodeConfig.getGlobalConfig();
        RelationConfig relationConfig = globalConfig.getRelationConfig();
        RelationTable foreign = relationConfig.getForeign();
        RelationTable primary = relationConfig.getPrimary();
        String path=globalConfig.getParentPathResources()+"/"+adminAutoCodeConfig.getThymeleafPath()+"/"+primary.getExistParentPackage_()+"/"+relationConfig.getPrimary().getDataName()+"_list.html";
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
                //添加删除按钮
                if(s.contains("lay-event=\"del\"")){
                    String str = "\t<a class=\"layui-btn layui-btn-danger layui-btn-xs\" lay-event=\"relationDel\"><i class=\"layui-icon layui-icon-delete\"></i>关联删除</a>\n";
                    if(oldContent.indexOf(str)<0){
                        content.append(str+"\n");
                    }else {
                        logger.error("重复代码过滤->\n{}",str);
                    }
                }



                //添加外表列
                if(s.contains("toolbar: '#barDemo'")){
                    String format = String.format(",{title: '%s',templet: function(d){\n" +
                                    "                        return '<a class=\"layui-btn layui-btn-xs\" onclick=\"openUrl(\\'%s/gotoList?fkey=%s&fvalue='+d.%s+'\\')\">" +
                                    "<i class=\"layui-icon layui-icon-search\"></i>详情</a>';\n" +
                                    "                    }\n" +
                                    "                }",  foreign.getRemark(), foreign.getBeanNameLower(),
                            foreign.getForeignKeyUp(false), primary.getPrimaryKeyUp(false));
                    if(oldContent.indexOf(format)<0){
                        content.append("\t\t\t\t"+format+"\n");
                    }else {
                        logger.error("重复代码过滤->\n{}",format);
                    }
                }

                //添加事件监听
                if(s.contains("table.on('tool(table-data)'")){
                    String format = String.format("if(obj.event === 'relationDel'){\n" +
                                    "                myConfirm(\"确定要关联删除吗?(删除后外表的记录也将清空)\", function (index) {\n" +
                                    "                    obj.del();\n" +
                                    "                    $.post(rootPath + \"%s/delete%sAnd%s\", {\"%s\": obj.data.%s}, function (data) {\n" +
                                    "                        myAlert(\"删除成功\");\n" +
                                    "                        setTimeout(function () {\n" +
                                    "                            loadMyData()\n" +
                                    "                        }, 2000)\n" +
                                    "                    });\n" +
                                    "                    layer.close(index);\n" +
                                    "                })\n" +
                                    "            }", primary.getBeanNameLower(), primary.getBeanName(), foreign.getBeanName(),
                            primary.getPrimaryKeyUp(false), primary.getPrimaryKeyUp(false));
                    if(oldContent.indexOf(format)<0){
                        content.append("\t\t\t"+format+"\n");
                    }else {
                        logger.error("重复代码过滤->\n{}",format);
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
