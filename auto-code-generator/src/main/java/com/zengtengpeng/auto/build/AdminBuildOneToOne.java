package com.zengtengpeng.auto.build;

import com.zengtengpeng.auto.config.AdminAutoCodeConfig;
import com.zengtengpeng.autoCode.config.AutoCodeConfig;
import com.zengtengpeng.autoCode.config.GlobalConfig;
import com.zengtengpeng.autoCode.utils.MyStringUtils;
import com.zengtengpeng.page.build.BuildOneToOneListPage;
import com.zengtengpeng.relation.bean.RelationTable;
import com.zengtengpeng.relation.config.RelationConfig;
import com.zengtengpeng.relation.oneToOne.BuildOneToOne;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;

/**
 * 自定义一对一实现
 */
public class AdminBuildOneToOne implements BuildOneToOne {

    Logger logger = LoggerFactory.getLogger(AdminBuildOneToOne.class);
    @Override
    public void custom(AutoCodeConfig autoCodeConfig) {
        AdminAutoCodeConfig adminAutoCodeConfig= (AdminAutoCodeConfig) autoCodeConfig;
        BuildOneToOneListPage buildOneToOneListPage=new BuildOneToOneListPage();
        buildOneToOneListPage.build(adminAutoCodeConfig);
    }

}
