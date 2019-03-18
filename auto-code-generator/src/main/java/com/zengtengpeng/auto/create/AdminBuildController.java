package com.zengtengpeng.auto.create;

import com.zengtengpeng.autoCode.bean.BuildJavaMethod;
import com.zengtengpeng.autoCode.config.AutoCodeConfig;
import com.zengtengpeng.autoCode.config.BuildJavaConfig;
import com.zengtengpeng.autoCode.config.GlobalConfig;
import com.zengtengpeng.autoCode.create.BuildController;
import com.zengtengpeng.autoCode.utils.MyStringUtils;
import com.zengtengpeng.jdbc.bean.Bean;
import com.zengtengpeng.jdbc.bean.BeanColumn;

import java.util.ArrayList;
import java.util.List;

/**
 * 扩展构建admin controller方法
 */
public class AdminBuildController implements BuildController {
    @Override
    public BuildJavaConfig custom(AutoCodeConfig autoCodeConfig) {
        //自定义导入
        BuildJavaConfig buildJavaConfig=new BuildJavaConfig();
        buildJavaConfig.setImports(buildImports(autoCodeConfig));


        //自定义方法
        List<BuildJavaMethod> ms=new ArrayList<>();
        ms.add(buildGotoList(autoCodeConfig));
        ms.add(buildGotoDetail(autoCodeConfig));
        buildJavaConfig.setBuildJavaMethods(ms);

        return buildJavaConfig;
    }

    /**
     * 自定义导入
     * @return
     * @param autoCodeConfig
     */
    public List<String> buildImports(AutoCodeConfig autoCodeConfig){
        List<String> list=new ArrayList<>();
        list.add("com.zengtengpeng.common.annotation.Auth");
        list.add("springfox.documentation.annotations.ApiIgnore");
        return list;
    }

    /**
     * 构建 跳转到列表页面 方法
     * @param autoCodeConfig
     * @return
     */
    public BuildJavaMethod buildGotoList(AutoCodeConfig autoCodeConfig){
        BuildJavaMethod buildJavaMethod=new BuildJavaMethod();
        List<String> an=new ArrayList<>();
        Bean bean = autoCodeConfig.getBean();
        an.add("@RequestMapping(\""+bean.getTableValue()+"/gotoList\")");
        if(autoCodeConfig.getGlobalConfig().getSwagger()){
            an.add("@ApiIgnore");
        }
        buildJavaMethod.setAnnotation(an);
        buildJavaMethod.setRemark("跳转到列表页面");
        List<String> params=new ArrayList<>();
        params.add(bean.getTableName()+" "+bean.getTableValue());
        params.add("HttpServletRequest request");
        params.add("HttpServletResponse response");
        buildJavaMethod.setParams(params);
        buildJavaMethod.setMethodName("gotoList");
        buildJavaMethod.setMethodType("public");
        buildJavaMethod.setReturnType("String");
        buildJavaMethod.setContent(String.format("return \"%s/%s_list\";",bean.getMobelName(),bean.getDataName()));
        return buildJavaMethod;
    }

    /**
     * 构建 跳转到详情页面 方法
     * @param autoCodeConfig
     * @return
     */
    public BuildJavaMethod buildGotoDetail(AutoCodeConfig autoCodeConfig){
        BuildJavaMethod buildJavaMethod=new BuildJavaMethod();
        List<String> an=new ArrayList<>();
        Bean bean = autoCodeConfig.getBean();
        GlobalConfig globalConfig = autoCodeConfig.getGlobalConfig();
        an.add("@RequestMapping(\""+bean.getTableValue()+"/gotoDetail\")");
        an.add(String.format("@Auth(\"%s/save\")",bean.getTableValue()));
        if(autoCodeConfig.getGlobalConfig().getSwagger()){
            an.add("@ApiIgnore");
        }
        buildJavaMethod.setAnnotation(an);
        buildJavaMethod.setRemark("跳转到详情页面");
        List<String> params=new ArrayList<>();
        params.add(bean.getTableName()+" "+bean.getTableValue());
        params.add("HttpServletRequest request");
        params.add("HttpServletResponse response");
        buildJavaMethod.setParams(params);
        buildJavaMethod.setMethodName("gotoDetail");
        buildJavaMethod.setMethodType("public");
        buildJavaMethod.setReturnType("String");
        StringBuffer content=new StringBuffer();
        BeanColumn beanColumn = bean.getPrimaryKey().get(0);
        MyStringUtils.append(content,"if(%s.get%s()!=null){",bean.getTableValue(),beanColumn.getBeanName_());
        MyStringUtils.append(content," request.setAttribute(\"%s\",%s%s.selectByPrimaryKey(%s));",
                3,bean.getDataName(),bean.getTableValue(),globalConfig.getPackageServiceUp(),bean.getTableValue());

        MyStringUtils.append(content,"}else {",2);
        MyStringUtils.append(content,"request.setAttribute(\"%s\",%s);",3,bean.getDataName(),bean.getTableValue());
        MyStringUtils.append(content,"}",2,bean.getDataName(),bean.getTableValue());
        MyStringUtils.append(content,"return \"%s/%s_detail\";",2,bean.getMobelName(),bean.getDataName());

        buildJavaMethod.setContent(content.toString());
        return buildJavaMethod;
    }

}
