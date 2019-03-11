package com.zengtengpeng.auto.build;

import com.zengtengpeng.auto.config.AdminAutoCodeConfig;
import com.zengtengpeng.autoCode.config.AutoCodeConfig;
import com.zengtengpeng.extend.build.manyToMany.ExtendManyToManyController;
import com.zengtengpeng.extend.build.manyToMany.ExtendManyToManyDetailPage;
import com.zengtengpeng.extend.build.manyToMany.ExtendManyToManyListPage;
import com.zengtengpeng.relation.manyToMany.BuildManyToMany;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * 自定义多对多实现
 */
public class AdminBuildManyToMany implements BuildManyToMany {

    Logger logger = LoggerFactory.getLogger(AdminBuildManyToMany.class);
    @Override
    public void custom(AutoCodeConfig autoCodeConfig) {
        AdminAutoCodeConfig adminAutoCodeConfig= (AdminAutoCodeConfig) autoCodeConfig;

        ExtendManyToManyListPage buildOneToOneListPage=new ExtendManyToManyListPage();
        buildOneToOneListPage.build(adminAutoCodeConfig);

        ExtendManyToManyController extendOneToOneController=new ExtendManyToManyController();
        extendOneToOneController.build(adminAutoCodeConfig);

        ExtendManyToManyDetailPage extendOneToOneDetailpage=new ExtendManyToManyDetailPage();
        extendOneToOneDetailpage.build(adminAutoCodeConfig);
    }

}
