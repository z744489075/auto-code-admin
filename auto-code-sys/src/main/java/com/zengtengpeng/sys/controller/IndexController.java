package com.zengtengpeng.sys.controller;

import com.zengtengpeng.common.annotation.Pass;
import com.zengtengpeng.sys.bean.SysAuth;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class IndexController {

    /**
     * 跳转到列表页面
     * @return
     */
    @RequestMapping("/index/gotoIndex")
    @Pass
    public String gotoIndex(SysAuth sysAuth, HttpServletRequest request, HttpServletResponse response){
        return "index";
    }



}
