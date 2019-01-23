package com.zengtengpeng.common;

import com.zengtengpeng.generator.bean.StartCode;
import com.zengtengpeng.generator.utils.AutoCodeUtils;

import java.util.Arrays;
import java.util.List;

public class AutoCodeTest {
    public static void main(String[] args) {
        List<String> dataNames= Arrays.asList("sys_operation_log","sys_login_log");
        StartCode startCode=new StartCode();
        startCode.setJdbc("jdbc:mysql://localhost:3306/auto_code");
        startCode.setUser("root");
        startCode.setPassword("111111");
        startCode.setDataNames(dataNames);
        startCode.setParentPath("E:\\resource\\workspaceJDB\\auto-code-admin\\auto-code-sys");
        startCode.setParentPack("com.zengtengpeng.sys");
        AutoCodeUtils.startByWebAdmin(startCode);
    }
}
