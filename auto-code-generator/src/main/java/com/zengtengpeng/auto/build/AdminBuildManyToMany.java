package com.zengtengpeng.auto.build;

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

        ExtendManyToManyListPage buildOneToOneListPage=new ExtendManyToManyListPage();
        buildOneToOneListPage.build(autoCodeConfig);

        ExtendManyToManyController extendOneToOneController=new ExtendManyToManyController();
        extendOneToOneController.build(autoCodeConfig);

        ExtendManyToManyDetailPage extendOneToOneDetailpage=new ExtendManyToManyDetailPage();
        extendOneToOneDetailpage.build(autoCodeConfig);
    }

}
