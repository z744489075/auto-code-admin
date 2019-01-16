package com.zengtengpeng.common.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 主页
 */
@Controller
public class IndexController {

    /**
     * 跳转到主页
     * @return
     */
    @RequestMapping("/index/gotoIndex")
    public String gotoIndex(){
        return "/index";
    }
}
