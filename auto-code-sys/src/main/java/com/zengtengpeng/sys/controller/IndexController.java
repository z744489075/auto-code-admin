package com.zengtengpeng.sys.controller;

import com.zengtengpeng.common.annotation.Auth;
import com.zengtengpeng.common.annotation.Pass;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import springfox.documentation.annotations.ApiIgnore;

@Controller
@ApiIgnore
public class IndexController  {

    /**
     * 跳转到列表页面
     * @return
     */
    @RequestMapping(value = {"index/gotoIndex",""})
    @Auth
    public String gotoIndex(){
        return "index";
    }
    /**
     * 跳转到新版欢迎页面
     * @return
     */
    @RequestMapping("index/gotoWelcome")
    @Pass
    public String gotoWelcome(){
        return "welcome";
    }
    /**
     * 跳转到旧版欢迎页面
     * @return
     */
    @RequestMapping("index/gotoWelcomeOld")
    @Pass
    public String gotoWelcomeOld(){
        return "welcome_old";
    }




}
