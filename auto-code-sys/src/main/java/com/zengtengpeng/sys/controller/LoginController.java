package com.zengtengpeng.sys.controller;

import com.zengtengpeng.common.annotation.Pass;
import com.zengtengpeng.common.enums.ResponseCode;
import com.zengtengpeng.common.bean.DataRes;
import com.zengtengpeng.sys.bean.SysAuth;
import com.zengtengpeng.sys.bean.SysLoginLog;
import com.zengtengpeng.sys.bean.SysUser;
import com.zengtengpeng.sys.constant.SessionConstant;
import com.zengtengpeng.sys.service.SysAuthService;
import com.zengtengpeng.sys.service.SysLoginLogService;
import com.zengtengpeng.sys.service.SysUserService;
import com.zengtengpeng.utils.AuthTreeUtils;
import com.zengtengpeng.utils.RandomCodeUtil;
import com.zengtengpeng.utils.RequestUtils;
import com.zengtengpeng.utils.UserUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 登录控制器
 */
@Controller
public class LoginController {

    @Resource
    private SysUserService sysUserService;
    @Resource
    private SysAuthService sysAuthService;
    @Resource
    private SysLoginLogService sysLoginLogService;

    @Value("${auto.code.admin}")
    private String admin;
    /**
     * 跳转到登录页面
     * @return
     */
    @RequestMapping("login/gotoLogin")
    @Pass
    public String gotoLogin(){
        return "login";
    }

    /**
     * 获取图形验证码
     * @return
     */
    @RequestMapping("login/imageCode")
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
    @RequestMapping("login/loginout")
    @Pass
    public String loginout(HttpSession session){
        UserUtils.removeUser(session);
        return "login";
    }

    /**
     * 跳转到登录页面
     * @return
     */
    @RequestMapping("login/login")
    @ResponseBody
    @Pass
    public DataRes login(SysUser sysUser, HttpServletRequest request){

        if(!RandomCodeUtil.isValidImageVerifyCode(request,"verifyCode")){
            return DataRes.error(ResponseCode.LOGIN_IMAGECODE.code(),ResponseCode.LOGIN_IMAGECODE.desc());
        }
        SysUser param=new SysUser();
        param.setLoginName(sysUser.getLoginName());
        List<SysUser> sysUsers = sysUserService.selectByCondition(param);
        if(sysUsers.size()>0){
            SysUser data = sysUsers.get(0);
            if(data.getStatus().equals(1)){
                return DataRes.error(ResponseCode.LOGIN_UNSATUS.code(),ResponseCode.LOGIN_UNSATUS.desc());
            }
            if (!data.getPassword().equals(DigestUtils.md5DigestAsHex(sysUser.getPassword().getBytes()))){
               return DataRes.error(ResponseCode.LOGIN_UNPASSWORD.code(),ResponseCode.LOGIN_UNPASSWORD.desc());
            }
            List<SysAuth> recurve;
            List<SysAuth> sysAuths;
            HttpSession session = request.getSession();
            //如果是超级管理员查询所有的权限
            if(admin.equals(sysUser.getLoginName())){
                SysAuth sysAuth=new SysAuth();
                sysAuth.setOrderByString(" order by sort asc");
                sysAuths = sysAuthService.selectAll(sysAuth);
                recurve = AuthTreeUtils.recurve(sysAuths);
            }else{
                sysAuths = sysAuthService.queryByUser(data.getId());
                recurve = AuthTreeUtils.recurve(sysAuths);

                //遍历, 已url为key存放用户权限到session中
                Map<String,SysAuth> userAuth=new HashMap<>();
                sysAuths.forEach(t-> userAuth.put(t.getHref(),t));
                session.setAttribute(SessionConstant.userAuth,userAuth);
            }

            Map<Integer,SysAuth> authAllById=new HashMap<>();
            Map<String,SysAuth> authAllByHref=new HashMap<>();
            sysAuths.forEach(t->{
                authAllById.put(t.getId(),t);
                authAllByHref.put(t.getHref(),t);
            });
            session.setAttribute(SessionConstant.authAllById,authAllById);
            session.setAttribute(SessionConstant.authAllByHref,authAllByHref);

            session.setAttribute(SessionConstant.auths,recurve);
            UserUtils.loginUser(data, session);

            SysLoginLog sysLogin=new SysLoginLog();
            sysLogin.setBrowser(RequestUtils.getOsAndBrowserInfo(request));
            sysLogin.setIp(RequestUtils.getIp(request));
            sysLogin.setSysUserId(data.getId());
            sysLoginLogService.insert(sysLogin);
            return DataRes.success(data);
        }else {
           return DataRes.error(ResponseCode.LOGIN_UNUSERNAME.code(),ResponseCode.LOGIN_UNUSERNAME.desc());
        }

    }
}
