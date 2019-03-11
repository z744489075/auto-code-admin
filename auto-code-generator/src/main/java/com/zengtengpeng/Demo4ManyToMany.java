package com.zengtengpeng;


import com.zengtengpeng.auto.AdminStartCode;
import com.zengtengpeng.auto.build.AdminBuildManyToMany;
import com.zengtengpeng.auto.build.AdminBuildOneToMany;
import com.zengtengpeng.autoCode.StartCode;
import com.zengtengpeng.relation.utils.RelationUtils;

/**
 * 多对多生成实例 test_user 多个用户 对应 test_role 多个角色
 */
public class Demo4ManyToMany {
    public static void main(String[] args) {
        RelationUtils.manyToMany(AdminStartCode.saxYaml("auto-code_many-to-many.yaml"), new AdminStartCode(), new AdminBuildManyToMany());
    }
}
