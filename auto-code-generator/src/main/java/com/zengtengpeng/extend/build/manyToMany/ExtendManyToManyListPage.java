package com.zengtengpeng.extend.build.manyToMany;

import com.zengtengpeng.auto.config.AdminAutoCodeConfig;
import com.zengtengpeng.autoCode.config.GlobalConfig;
import com.zengtengpeng.extend.base.ExtendBaseListPage;
import com.zengtengpeng.relation.bean.RelationTable;
import com.zengtengpeng.relation.config.RelationConfig;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

/**
 * 构建一对一追加列表页方法
 */
public class ExtendManyToManyListPage implements ExtendBaseListPage {

    /**
     * 修改外表的 list页面
     * @param adminAutoCodeConfig
     */
    public void foreignChangeListPage(AdminAutoCodeConfig adminAutoCodeConfig){
        GlobalConfig globalConfig = adminAutoCodeConfig.getGlobalConfig();
        RelationConfig relationConfig = globalConfig.getRelationConfig();
        RelationTable foreign = relationConfig.getForeign();
        RelationTable primary = relationConfig.getPrimary();
        RelationTable thirdparty = relationConfig.getThirdparty();
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

                content.append(s+"\n");
                //添加删除按钮
                if(s.contains("lay-event=\"del\"")){
                    String str = String.format("\t<a class=\"layui-btn layui-btn-danger layui-btn-xs\" lay-event=\"relation_del_%s\"><i class=\"layui-icon layui-icon-delete\"></i>关联删除%s</a>\n",primary.getDataName(),primary.getRemark());
                    if(oldContent.indexOf(str)<0){
                        content.append(str+"\n");
                    }else {
                        logger.error("重复代码过滤->\n{}",str);
                    }
                }

                //添加外表列
                if(s.contains("toolbar: '#barDemo'")){
                    String format = String.format(",{title: '%s',templet: function(d){\n" +
                                    "                        return '<a class=\"layui-btn layui-btn-xs\" onclick=\"openUrl(\\'%s/gotoList?furl=%s/select%sBy%s&fkey=%s&fvalue='+d.%s+'\\')\"><i class=\"layui-icon layui-icon-search\"></i>详情</a>';\n" +
                                    "                    }\n" +
                                    "                }", primary.getRemark(), primary.getBeanNameLower(),
                            primary.getBeanNameLower(),primary.getBeanName(),foreign.getBeanName(),
                            thirdparty.getForeignKeyUp(false), foreign.getForeignKeyUp(false));
                    if(oldContent.indexOf(format)<0){
                        content.append("\t\t\t\t"+format+"\n");
                    }else {
                        logger.error("重复代码过滤->\n{}",format);
                    }
                }

                //添加事件监听
                if(s.contains("table.on('tool(table-data)'")){
                    String format = String.format("if(obj.event === 'relation_del_%s'){\n" +
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
                                    "            }",primary.getDataName(), foreign.getBeanNameLower(),primary.getBeanName(), foreign.getBeanName(),
                            foreign.getForeignKeyUp(false), foreign.getForeignKeyUp(false));
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
    /**
     * 修改主表的 list页面
     * @param adminAutoCodeConfig
     */
    public void primaryChangeListPage(AdminAutoCodeConfig adminAutoCodeConfig){
        GlobalConfig globalConfig = adminAutoCodeConfig.getGlobalConfig();
        RelationConfig relationConfig = globalConfig.getRelationConfig();
        RelationTable foreign = relationConfig.getForeign();
        RelationTable primary = relationConfig.getPrimary();
        RelationTable thirdparty = relationConfig.getThirdparty();
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
                    String str = String.format("\t<a class=\"layui-btn layui-btn-danger layui-btn-xs\" lay-event=\"relation_del_%s\"><i class=\"layui-icon layui-icon-delete\"></i>关联删除%s</a>\n",foreign.getDataName(),foreign.getRemark());
                    if(oldContent.indexOf(str)<0){
                        content.append(str+"\n");
                    }else {
                        logger.error("重复代码过滤->\n{}",str);
                    }
                }



                //添加外表列
                if(s.contains("toolbar: '#barDemo'")){
                    String format = String.format(",{title: '%s',templet: function(d){\n" +
                                    "                        return '<a class=\"layui-btn layui-btn-xs\" onclick=\"openUrl(\\'%s/gotoList?furl=%s/select%sBy%s&fkey=%s&fvalue='+d.%s+'\\')\">" +
                                    "<i class=\"layui-icon layui-icon-search\"></i>详情</a>';\n" +
                                    "                    }\n" +
                                    "                }",  foreign.getRemark(), foreign.getBeanNameLower(),
                            foreign.getBeanNameLower(),foreign.getBeanName(),primary.getBeanName(),
                            thirdparty.getPrimaryKeyUp(false), primary.getPrimaryKeyUp(false));
                    if(oldContent.indexOf(format)<0){
                        content.append("\t\t\t\t"+format+"\n");
                    }else {
                        logger.error("重复代码过滤->\n{}",format);
                    }
                }
                //添加事件监听
                if(s.contains("table.on('tool(table-data)'")){
                    String format = String.format("if(obj.event === 'relation_del_%s'){\n" +
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
                                    "            }",foreign.getDataName(), primary.getBeanNameLower(), primary.getBeanName(), foreign.getBeanName(),
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
