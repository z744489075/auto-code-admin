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
 * 多表生成页面追加详情页面
 */
public interface ExtendBaseDetailPage {

    Logger logger = LoggerFactory.getLogger(ExtendBaseDetailPage.class);

    /**
     * 构建
     * @param adminAutoCodeConfig
     */
    default void build(AdminAutoCodeConfig adminAutoCodeConfig){
        foreignDetailPage(adminAutoCodeConfig);
    }
    /**
     * 修改外表的 详情页面
     * @param adminAutoCodeConfig
     */
    default void foreignDetailPage(AdminAutoCodeConfig adminAutoCodeConfig){
        GlobalConfig globalConfig = adminAutoCodeConfig.getGlobalConfig();
        RelationConfig relationConfig = globalConfig.getRelationConfig();
        RelationTable foreign = relationConfig.getForeign();
        RelationTable primary = relationConfig.getPrimary();
        String path=globalConfig.getParentPathResources()+"/"+adminAutoCodeConfig.getThymeleafPath()+"/"+foreign.getExistParentPackage_()+"/"+foreign.getDataName()+"_detail.html";
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
                if(s.contains("id=\"userId\"")){
                    //<select name="userId" id="userId" xm-select="select13" xm-select-radio="" xm-select-search="" lay-verify="required">
                    //                        <option  th:each="rowdata:${goto_detail_users}" th:value="${rowdata.id}" th:selected="${test_class.userId==rowdata.id}"  th:text="${rowdata.id}"></option>
                    //                    </select>
                    StringBuffer text=new StringBuffer();
                    String foreignKeyUp = foreign.getForeignKeyUp(false);
                    MyStringUtils.append(text,"<select name=\"%s\" id=\"%s\" xm-select=\"select13\" xm-select-radio=\"\" xm-select-search=\"\" lay-verify=\"required\">",5,
                            foreignKeyUp,foreignKeyUp);
                    String primaryKeyUp = primary.getPrimaryKeyUp(false);
                    MyStringUtils.append(text,"<option  th:each=\"rowdata:${goto_detail_%s}\" th:value=\"${rowdata.%s}\" " +
                                    "th:selected=\"${%s.%s==rowdata.%s}\"  th:text=\"${rowdata.%s}\"></option>",6,
                            primary.getDataName(), primaryKeyUp,foreign.getDataName(),foreignKeyUp,primaryKeyUp,primaryKeyUp);
                    MyStringUtils.append(text,"</select>",5);
                    if(oldContent.indexOf(text.toString())<0){
                        content.append(text.toString()+"\n");
                    }else {
                        content.append(s+"\n");
                        logger.error("重复代码过滤->\n{}",text);
                    }
                }else {
                    content.append(s+"\n");
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
