package com.zengtengpeng.sys.controller;

import com.zengtengpeng.common.annotation.Auth;
import com.zengtengpeng.common.annotation.Pass;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController  {

    /**
     * 跳转到列表页面
     * @return
     */
    @RequestMapping(value = {"/index/gotoIndex","/"})
    @Auth
    public String gotoIndex(){
        return "index";
    }
    /**
     * 跳转到列表页面
     * @return
     */
    @RequestMapping("index/gotoWelcome")
    @Pass
    public String gotoWelcome(){
        return "welcome";
    }




}
