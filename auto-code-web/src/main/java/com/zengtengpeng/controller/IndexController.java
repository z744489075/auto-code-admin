package com.zengtengpeng.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
public class IndexController {

    @RequestMapping("/index/gotoIndex")
    public String gotoIndex(){
        return "index";
    }
    @RequestMapping("/index/testList")
    public String testList(){
        return "system-category";
    }
    @RequestMapping("/index/testList1")
    public String testList11(){
        return "system-category-laui";
    }
    @RequestMapping("/index/testData")
    @ResponseBody
    public List<String> testList1(){
        List<String> list=new ArrayList<>();
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");
        list.add("5");
        list.add("6");
        list.add("7");
        return list;
    }
}
