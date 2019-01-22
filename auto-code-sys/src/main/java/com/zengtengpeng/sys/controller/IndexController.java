package com.zengtengpeng.sys.controller;

import com.zengtengpeng.common.annotation.Pass;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
public class IndexController {

    /**
     * 跳转到列表页面
     * @return
     */
    @RequestMapping("/index/gotoIndex")
    @Pass
    public String gotoIndex(HttpSession session){
        return "index";
    }

    /**
     * 跳转404
     * @return
     */
    @RequestMapping("/index/goto404")
    @Pass
    public String goto404(HttpSession session){
        return "404";
    }
    /**
     * 跳转403
     * @return
     */
    @RequestMapping("/index/goto403")
    @Pass
    public String goto403(HttpSession session){
        return "403";
    }



}
