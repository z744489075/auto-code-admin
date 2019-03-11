
package com.zengtengpeng;


import com.zengtengpeng.auto.AdminStartCode;
import com.zengtengpeng.auto.build.AdminBuildOneToMany;
import com.zengtengpeng.auto.build.AdminBuildOneToOne;
import com.zengtengpeng.autoCode.StartCode;
import com.zengtengpeng.relation.utils.RelationUtils;

/**
 * 一对多生成实例 test_user 一个用户 对应 test_addr 多个收货地址
 */
public class Demo3OneToMany {
    public static void main(String[] args) {
        RelationUtils.oneToMany(AdminStartCode.saxYaml("auto-code_one-to-many.yaml"), new AdminStartCode(), new AdminBuildOneToMany());
    }
}
