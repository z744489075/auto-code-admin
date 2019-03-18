package com.zengtengpeng.common.controller;

import com.zengtengpeng.common.annotation.Pass;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpSession;

@Controller
@ApiIgnore
public class ErrorPageController implements ErrorController {

    @Override
    public String getErrorPath() {
        return "404";
    }

    @RequestMapping("error")
    @Pass
    public String error(){
        return getErrorPath();
    }

    /**
     * 跳转403
     * @return
     */
    @RequestMapping("/error/goto403")
    @Pass
    public String goto403(){
        return "403";
    }
    /**
     * 跳转500
     * @return
     */
    @RequestMapping("/error/goto500")
    @Pass
    public String goto500(){
        return "500";
    }
}
