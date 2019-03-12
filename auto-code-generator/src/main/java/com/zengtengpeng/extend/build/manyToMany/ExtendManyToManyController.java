package com.zengtengpeng.extend.build.manyToMany;

import com.zengtengpeng.auto.config.AdminAutoCodeConfig;
import com.zengtengpeng.autoCode.config.GlobalConfig;
import com.zengtengpeng.autoCode.utils.MyStringUtils;
import com.zengtengpeng.extend.base.ExtendBaseController;
import com.zengtengpeng.relation.bean.RelationTable;
import com.zengtengpeng.relation.config.RelationConfig;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

/**
 * 扩展一对一
 */
public class ExtendManyToManyController implements ExtendBaseController {

    /**
     * 构建
     * @param adminAutoCodeConfig
     */
    public void build(AdminAutoCodeConfig adminAutoCodeConfig){
        foreignGotoDetail(adminAutoCodeConfig);
        primaryGotoDetail(adminAutoCodeConfig);
        foreignAuth(adminAutoCodeConfig);
        primaryAuth(adminAutoCodeConfig);
    }
    /**
     * 修改外表的 gotoDetail 方法
     * @param adminAutoCodeConfig
     */
    public void foreignGotoDetail(AdminAutoCodeConfig adminAutoCodeConfig){
        GlobalConfig globalConfig = adminAutoCodeConfig.getGlobalConfig();
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
            boolean detailFlag=false;
            while ((s=bufferedReader.readLine())!=null){
                if(detailFlag&&s.contains("request.setAttribute(\""+foreign.getDataName()+"\",")){
                    detailFlag=false;
                    MyStringUtils.append(content,"%s t = %s%s.select%sAnd%sByCondition(%s).get(0);",3,
                            foreign.getBeanName(),foreign.getBeanNameLower(),globalConfig.getPackageServiceUp(),primary.getBeanName(),
                            foreign.getBeanName(),foreign.getBeanNameLower());
                    MyStringUtils.append(content,"request.setAttribute(\"%s\",t);",3,
                            foreign.getDataName());
                    MyStringUtils.append(content,"List<%s> list = t.get%sList();",3,primary.getBeanName(),primary.getBeanName());
                    MyStringUtils.append(content,"List<String> sId=new ArrayList<>();",3);
                    MyStringUtils.append(content,"if(list!=null){",3);
                    MyStringUtils.append(content,"for (%s %s : list) {",4,primary.getBeanName(),primary.getBeanNameLower());
                    MyStringUtils.append(content,"sId.add(%s.get%s().toString());",5,primary.getBeanNameLower(),primary.getPrimaryKeyUp(true));
                    MyStringUtils.append(content,"}",4);
                    MyStringUtils.append(content,"}",3);
                    MyStringUtils.append(content,"request.setAttribute(\"sId\",sId);\n",3);
                }else {
                    content.append(s+"\n");
                }
                //添加外表列
                if(s.contains("gotoDetail(")){
                    StringBuffer text=new StringBuffer();
                    MyStringUtils.append(text,"List<%s> data = %s%s.selectAll(null);",2,primary.getBeanName(),primary.getBeanNameLower(),globalConfig.getPackageServiceUp());
                    MyStringUtils.append(text,"request.setAttribute(\"goto_detail_%s\",data);",2,primary.getDataName());
                    if(oldContent.indexOf(text.toString())<0){
                        detailFlag=true;
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
     * 修改主表的 gotoDetail 方法
     * @param adminAutoCodeConfig
     */
    public void primaryGotoDetail(AdminAutoCodeConfig adminAutoCodeConfig){
        GlobalConfig globalConfig = adminAutoCodeConfig.getGlobalConfig();
        RelationConfig relationConfig = globalConfig.getRelationConfig();
        RelationTable foreign = relationConfig.getForeign();
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
            StringBuffer oldContent=new StringBuffer();
            fileReader=new FileReader(file);
            bufferedReader=new BufferedReader(fileReader);
            bufferedReader.mark((int) file.length());
            String s;
            while ((s=bufferedReader.readLine())!=null){
                oldContent.append(s+"\n");
            }
            bufferedReader.reset();
            boolean detailFlag=false;
            while ((s=bufferedReader.readLine())!=null){
                if(detailFlag&&s.contains("request.setAttribute(\""+primary.getDataName()+"\",")){
                    detailFlag=false;
                    MyStringUtils.append(content,"%s t = %s%s.select%sAnd%sByCondition(%s).get(0);",3,
                            primary.getBeanName(),primary.getBeanNameLower(),globalConfig.getPackageServiceUp(),primary.getBeanName(),foreign.getBeanName(),
                            primary.getBeanNameLower());
                    MyStringUtils.append(content,"request.setAttribute(\"%s\",t);",3,
                            primary.getDataName());
                    MyStringUtils.append(content,"List<%s> list = t.get%sList();",3,foreign.getBeanName(),foreign.getBeanName());
                    MyStringUtils.append(content,"List<String> sId=new ArrayList<>();",3);
                    MyStringUtils.append(content,"if(list!=null){",3);
                    MyStringUtils.append(content,"for (%s %s : list) {",4,foreign.getBeanName(),foreign.getBeanNameLower());
                    MyStringUtils.append(content,"sId.add(%s.get%s().toString());",5,foreign.getBeanNameLower(),foreign.getForeignKeyUp(true));
                    MyStringUtils.append(content,"}",4);
                    MyStringUtils.append(content,"}",3);
                    MyStringUtils.append(content,"request.setAttribute(\"sId\",sId);\n",3);
                }else {
                    content.append(s+"\n");
                }
                //添加外表列
                if(s.contains("gotoDetail(")){
                    StringBuffer text=new StringBuffer();
                    MyStringUtils.append(text,"List<%s> data = %s%s.selectAll(null);",2,foreign.getBeanName(),foreign.getBeanNameLower(),globalConfig.getPackageServiceUp());
                    MyStringUtils.append(text,"request.setAttribute(\"goto_detail_%s\",data);",2,foreign.getDataName());
                    if(oldContent.indexOf(text.toString())<0){
                        detailFlag=true;
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
