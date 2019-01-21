package com.zengtengpeng.sys.utils;

import com.zengtengpeng.sys.bean.SysUser;
import com.zengtengpeng.sys.constant.SessionConstant;

import javax.servlet.http.HttpSession;

/**
 * 用户工具类
 */
public class UserUtils {
    /**
     * 登录
     * @param sysUser
     * @param session
     */
    public static void loginUser(SysUser sysUser, HttpSession session){
        session.setAttribute(SessionConstant.sysUserSession,sysUser);
    }

    /**
     * 获取用户
     * @param session
     */
    public static SysUser getUser(HttpSession session){
        Object attribute = session.getAttribute(SessionConstant.sysUserSession);
        if(attribute!=null){
            return (SysUser) attribute;
        }
        return null;
    }
}
