
package com.zengtengpeng;


import com.zengtengpeng.auto.AdminStartCode;
import com.zengtengpeng.auto.build.AdminBuildOneToMany;
import com.zengtengpeng.relation.utils.RelationUtils;

/**
 * 一对多生成实例
 */
public class Demo3OneToMany {
    public static void main(String[] args) {
        //auto-code_one-to-many.yaml 为配置文件名 不写默认是 auto-code.yaml
        RelationUtils.oneToMany(AdminStartCode.saxYaml("auto-code_one-to-many.yaml"), new AdminStartCode(), new AdminBuildOneToMany());
    }
}
