package com.zengtengpeng.utils;

import com.zengtengpeng.sys.bean.SysUser;
import com.zengtengpeng.sys.constant.SessionConstant;

import javax.servlet.http.HttpServletRequest;
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
    /**
     * 获取用户
     * @param request
     */
    public static SysUser getUser(HttpServletRequest request){

        return getUser(request.getSession());
    }

    /**
     * 移除用户登录
     * @param session
     */
    public static void removeUser(HttpSession session) {
        session.removeAttribute(SessionConstant.sysUserSession);
        session.removeAttribute(SessionConstant.userAuth);
    }
}
