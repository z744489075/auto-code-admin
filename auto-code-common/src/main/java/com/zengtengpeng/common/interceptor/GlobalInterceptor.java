package com.zengtengpeng.common.interceptor;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.resource.ResourceHttpRequestHandler;

import com.zengtengpeng.common.annotation.Pass;

/**
 * 全局拦截器 Created by zengtengpeng on 2016/1/11.
 */
public class GlobalInterceptor implements HandlerInterceptor {

    private ServletContext servletContext;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        //静态资源放行
        if (handler instanceof ResourceHttpRequestHandler) {
            return true;
        }

        // 查看该方法是否可以直接放行
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
        Pass annotation = method.getAnnotation(Pass.class);
        Map map=new HashMap();
        map.put("/testAutoCode2/gotoDetail","222");
        request.getSession().setAttribute("userAuth",null);
		return true;

       /* // 后台用户都要已admin开头,前台用户要以/front 开头
        String uri = request.getRequestURI();
        String uriAuth = null;
        String ajax = request.getHeader("X-Requested-With");
        uri = uri.replace(request.getContextPath(), "");

        // 获取全局权限表
        if (uri.startsWith("/")) {
            uriAuth = uri.substring(1);
        }

        if (uri.startsWith("/admin/")) {
            AdminUser adminUser = (AdminUser) request.getSession().getAttribute("adminUser");
            if (adminUser == null) {
                // 管理员未登录
                if ("XMLHttpRequest".equals(ajax)) {
                    response.setHeader("Content-Type", "application/json");
                    response.getWriter().print("{\"seesion\":\"overTime\"}");
                } else {
                    String url = "<script>if (window.parent != null) {window.parent.location.href ='" +
                            request.getContextPath() + "/page/login.jsp';} else { window.location.href ='" + request.getContextPath() + "/page/login.jsp';}  </script>";
//					response.sendRedirect(request.getContextPath() + "/page/login.jsp");
                    response.getWriter().print(url);
                }
                return false;
            } else {
                // 管理员已登录，是否有权限？
                // 如果是超级管理员，去掉权限验证：
                if (!"admin".equals(adminUser.getLoginName())) {
                    Authority authority = adminUser.getAuthorityMap().get(uriAuth);
                    if (authority == null) {
                        if ("XMLHttpRequest".equals(ajax)) {
                            response.setHeader("Content-Type", "application/json");
                            response.getWriter().print("{\"msg\":\"no right\"}");
                        } else {
                            response.sendRedirect(request.getContextPath() + "/page/noright.jsp");
                        }
                        return false;
                    }
                }
            }
            return true;
        } else if (uri.startsWith("/mobile/")) {
            User user = SessionUtils.getUser(request);
            if (user == null) {
                if("XMLHttpRequest".equals(ajax)) {
                    response.setHeader("Content-Type", "application/json");
                    response.getWriter().print("{\"seesion\":\"mobileOverTime\"}");
                } else {
                    response.sendRedirect(request.getContextPath() +"/page/mobile/login.jsp");
                }
                return false;
            }
            return true;
        }


        response.getWriter().print("Please enter a valid url");

        return false;*/
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {

    }
}
