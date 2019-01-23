package com.zengtengpeng.sys.constant;

public class SessionConstant {

    /**
     * 登录验证码
     */
    public  static String imageLgoinCode="imageLgoinCode";

    /**
     * 用户session
     */
    public  static String sysUserSession="sysUserSession";

    /**
     * 用户用户所有的权限(key=url value=权限) 超级管理员为空
     */
    public  static String userAuth="userAuth";

    /**
     * 用户权限(递归)
     */
    public static String auths="auths";
    /**
     * 用户所有的权限(key=id value=权限)
     */
    public static String authAllById="authAllById";
    /**
     * 用户所有的权限(key=href value=权限)
     */
    public static String authAllByHref="authAllByHref";
}
