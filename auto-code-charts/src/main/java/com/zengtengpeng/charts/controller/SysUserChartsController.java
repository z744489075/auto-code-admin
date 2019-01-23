package com.zengtengpeng.charts.controller;

import com.zengtengpeng.common.annotation.Auth;
import com.zengtengpeng.sys.service.SysUserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * 系统用户charts
 */
@Controller
public class SysUserChartsController {


    /**
     * 折线图
     * @return
     */
    @RequestMapping("/sysUserCharts/gotoLine")
    public String gotoLine(HttpServletRequest request){
        List<String> x= Arrays.asList("第一天", "第二天", "第三天", "第四天", "第五天", "第六天", "第七天");
        List<Integer> y= Arrays.asList(820, 932, 901, 934, 1290, 1330, 1320);
        request.setAttribute("x",x);
        request.setAttribute("y",y);
        return "line";
    }
    /**
     * 柱状图
     * @return
     */
    @RequestMapping("/sysUserCharts/gotoBar")
    public String gotoBar(HttpServletRequest request){
        List<String> x= Arrays.asList("第一天", "第二天", "第三天", "第四天", "第五天", "第六天", "第七天");
        List<Integer> y= Arrays.asList(820, 932, 901, 934, 1290, 1330, 1320);
        request.setAttribute("x",x);
        request.setAttribute("y",y);
        return "bar";
    }
    /**
     * 饼图
     * @return
     */
    @RequestMapping("/sysUserCharts/gotoPie")
    public String gotoPie(HttpServletRequest request){
        List<Map> datas=new ArrayList<>();
        for (int i=1;i<=5;i++){
            Map data=new HashMap();
            data.put("value",i+100);
            data.put("name","测试饼图"+i);
            datas.add(data);
        }
        request.setAttribute("data",datas);
        return "pie";
    }
}
