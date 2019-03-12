package com.zengtengpeng;


import com.zengtengpeng.auto.AdminStartCode;
import com.zengtengpeng.auto.build.AdminBuildOneToOne;
import com.zengtengpeng.relation.utils.RelationUtils;

/**
 * 一对一生成实例
 */
public class Demo2OneToOne {
    public static void main(String[] args) {
        //auto-code_one-to-one.yaml 为配置文件名 不写默认是 auto-code.yaml
        RelationUtils.oneToOne(AdminStartCode.saxYaml("auto-code_one-to-one.yaml"), new AdminStartCode(), new AdminBuildOneToOne());
    }
}
