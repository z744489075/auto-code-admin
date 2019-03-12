package com.zengtengpeng;

import com.zengtengpeng.auto.AdminStartCode;
import com.zengtengpeng.autoCode.StartCode;

/**
 * 单表生成实例
 */
public class Demo1simple {
    public static void main(String[] args) {
        StartCode startCode= new AdminStartCode();
        //auto-code_simple.yaml 为配置文件名 不写默认是 auto-code.yaml
        startCode.start(AdminStartCode.saxYaml("auto-code_simple.yaml"));
    }
}
