package com.zengtengpeng.sys.controller;

import com.zengtengpeng.common.annotation.Pass;
import com.zengtengpeng.common.annotation.ResponseCode;
import com.zengtengpeng.common.bean.DataRes;
import com.zengtengpeng.sys.bean.SysUser;
import com.zengtengpeng.sys.constant.SessionConstant;
import com.zengtengpeng.sys.service.SysUserService;
import com.zengtengpeng.sys.utils.RandomCodeUtil;
import com.zengtengpeng.sys.utils.UserUtils;
import org.springframework.stereotype.Controller;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * 登录控制器
 */
@Controller
public class LoginController {

    @Resource
    private SysUserService sysUserService;
    /**
     * 跳转到登录页面
     * @return
     */
    @RequestMapping("/login/gotoLogin")
    public String gotoLogin(){
        return "login";
    }

    /**
     * 获取图形验证码
     * @return
     */
    @RequestMapping("/login/imageCode")
    @Pass
    public void gotoIndex(HttpSession session,HttpServletResponse response){
        String rand = RandomCodeUtil.getVerifyCode(4);// 生成随机四位验证码
        session.setAttribute(SessionConstant.imageLgoinCode, rand);
        // 绘制验证码图片
        RandomCodeUtil.createValidateCode(response, rand);
    }

    /**
     * 跳转到登录页面
     * @return
     */
    @RequestMapping("/login/login")
    @ResponseBody
    public DataRes gotoLogin(SysUser sysUser, HttpServletRequest request){

        if(!RandomCodeUtil.isValidImageVerifyCode(request,"verifyCode")){
            return DataRes.error(ResponseCode.LOGIN_IMAGECODE.code(),ResponseCode.LOGIN_IMAGECODE.desc());
        }
        SysUser param=new SysUser();
        param.setLoginName(sysUser.getLoginName());
        List<SysUser> sysUsers = sysUserService.queryByCondition(param);
        if(sysUsers.size()>0){
            SysUser data = sysUsers.get(0);
            if (!data.getPassword().equals(DigestUtils.md5DigestAsHex(sysUser.getPassword().getBytes()))){
               return DataRes.error(ResponseCode.LOGIN_UNPASSWORD.code(),ResponseCode.LOGIN_UNPASSWORD.desc());
            }
            UserUtils.loginUser(data,request.getSession());
            return DataRes.success(data);
        }else {
           return DataRes.error(ResponseCode.LOGIN_UNUSERNAME.code(),ResponseCode.LOGIN_UNUSERNAME.desc());
        }

    }
}
