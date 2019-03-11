package com.zengtengpeng.auto.build;

import com.zengtengpeng.auto.config.AdminAutoCodeConfig;
import com.zengtengpeng.autoCode.config.AutoCodeConfig;
import com.zengtengpeng.extend.build.oneToOne.ExtendOneToOneController;
import com.zengtengpeng.extend.build.oneToOne.ExtendOneToOneDetailPage;
import com.zengtengpeng.extend.build.oneToOne.ExtendOneToOneListPage;
import com.zengtengpeng.relation.oneToOne.BuildOneToOne;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * 自定义一对一实现
 */
public class AdminBuildOneToOne implements BuildOneToOne {

    Logger logger = LoggerFactory.getLogger(AdminBuildOneToOne.class);
    @Override
    public void custom(AutoCodeConfig autoCodeConfig) {
        AdminAutoCodeConfig adminAutoCodeConfig= (AdminAutoCodeConfig) autoCodeConfig;

        ExtendOneToOneListPage buildOneToOneListPage=new ExtendOneToOneListPage();
        buildOneToOneListPage.build(adminAutoCodeConfig);

        ExtendOneToOneController extendOneToOneController=new ExtendOneToOneController();
        extendOneToOneController.build(adminAutoCodeConfig);

        ExtendOneToOneDetailPage extendOneToOneDetailpage=new ExtendOneToOneDetailPage();
        extendOneToOneDetailpage.build(adminAutoCodeConfig);
    }

}
