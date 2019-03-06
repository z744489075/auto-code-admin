package com.zengtengpeng;


import com.zengtengpeng.auto.AdminStartCode;
import com.zengtengpeng.auto.build.AdminBuildOneToOne;
import com.zengtengpeng.autoCode.StartCode;
import com.zengtengpeng.relation.oneToOne.BuildOneToOne;
import com.zengtengpeng.relation.utils.RelationUtils;

/**
 * 一对一生成实例 test_user 一个用户 对应 test_class 一个班级
 */
public class Demo2OneToOne {
    public static void main(String[] args) {

        //lambda表达式写法 二选一
        RelationUtils.oneToOne(AdminStartCode.saxYaml("auto-code_one-to-one.yaml"), new AdminStartCode(), new AdminBuildOneToOne());
    }
}
