package com.zengtengpeng.auto.build;

import com.zengtengpeng.autoCode.config.AutoCodeConfig;
import com.zengtengpeng.extend.build.oneToMany.ExtendOneToManyController;
import com.zengtengpeng.extend.build.oneToMany.ExtendOneToManyDetailPage;
import com.zengtengpeng.extend.build.oneToMany.ExtendOneToManyListPage;
import com.zengtengpeng.relation.oneToMany.BuildOneToMany;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * 自定义一对多实现
 */
public class AdminBuildOneToMany implements BuildOneToMany {

    Logger logger = LoggerFactory.getLogger(AdminBuildOneToMany.class);
    @Override
    public void custom(AutoCodeConfig autoCodeConfig) {

        ExtendOneToManyListPage buildOneToOneListPage=new ExtendOneToManyListPage();
        buildOneToOneListPage.build(autoCodeConfig);

        ExtendOneToManyController extendOneToOneController=new ExtendOneToManyController();
        extendOneToOneController.build(autoCodeConfig);

        ExtendOneToManyDetailPage extendOneToOneDetailpage=new ExtendOneToManyDetailPage();
        extendOneToOneDetailpage.build(autoCodeConfig);
    }

}
