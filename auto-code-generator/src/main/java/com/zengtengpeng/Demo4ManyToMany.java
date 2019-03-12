package com.zengtengpeng;


import com.zengtengpeng.auto.AdminStartCode;
import com.zengtengpeng.auto.build.AdminBuildManyToMany;
import com.zengtengpeng.relation.utils.RelationUtils;

/**
 * 多对多生成实例
 */
public class Demo4ManyToMany {
    public static void main(String[] args) {
        //auto-code_many-to-many.yaml 为配置文件名 不写默认是 auto-code.yaml
        RelationUtils.manyToMany(AdminStartCode.saxYaml("auto-code_many-to-many.yaml"), new AdminStartCode(), new AdminBuildManyToMany());
    }
}
