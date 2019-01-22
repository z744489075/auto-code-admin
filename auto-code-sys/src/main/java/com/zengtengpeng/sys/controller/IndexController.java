package com.zengtengpeng.sys.controller;

import com.zengtengpeng.common.annotation.Pass;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
public class IndexController  {

    /**
     * 跳转到列表页面
     * @return
     */
    @RequestMapping("/index/gotoIndex")
    @Pass
    public String gotoIndex(HttpSession session){
        return "index";
    }




}
