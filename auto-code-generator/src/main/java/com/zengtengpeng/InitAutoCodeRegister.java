package com.zengtengpeng;

import com.zengtengpeng.auto.AdminStartCode;
import com.zengtengpeng.auto.build.AdminBuildManyToMany;
import com.zengtengpeng.auto.build.AdminBuildOneToMany;
import com.zengtengpeng.auto.build.AdminBuildOneToOne;
import com.zengtengpeng.common.drive.AutoCodeDrive;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * 容器启动注册自定义代码生成器
 */
@Component
public class InitAutoCodeRegister implements ApplicationRunner {
    @Override
    public void run(ApplicationArguments args) throws Exception {
        AutoCodeDrive.setStartCode( new AdminStartCode());
        System.out.println(AutoCodeDrive.getStartCode());
        AutoCodeDrive.setBuildManyToMany(new AdminBuildManyToMany());
        AutoCodeDrive.setBuildOneToMany(new AdminBuildOneToMany());
        AutoCodeDrive.setBuildOneToOne(new AdminBuildOneToOne());
    }
}
