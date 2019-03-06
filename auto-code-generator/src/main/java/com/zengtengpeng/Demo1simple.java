package com.zengtengpeng;

import com.zengtengpeng.auto.AdminStartCode;
import com.zengtengpeng.autoCode.StartCode;

/**
 * 单表生成实例
 */
public class Demo1simple {
    public static void main(String[] args) {
        //lambda表达式写法 二选一
        StartCode startCode= new AdminStartCode();

        startCode.start(AdminStartCode.saxYaml("auto-code_simple.yaml"));
    }
}
