package com.zengtengpeng.sys.interceptor;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zengtengpeng.common.annotation.Auth;
import com.zengtengpeng.common.annotation.Pass;
import com.zengtengpeng.common.bean.DataRes;
import com.zengtengpeng.common.bean.Page;
import com.zengtengpeng.common.enums.ResponseCode;
import com.zengtengpeng.sys.bean.SysAuth;
import com.zengtengpeng.sys.bean.SysUser;
import com.zengtengpeng.sys.constant.SessionConstant;
import com.zengtengpeng.sys.utils.UserUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.resource.ResourceHttpRequestHandler;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * 用户权限拦截
 */
public class UserInterceptor implements HandlerInterceptor {

    @Value("${auto.code.admin}")
   private String admin;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        //静态资源放行
        if (handler instanceof ResourceHttpRequestHandler) {
            return true;
        }
        response.setCharacterEncoding("utf-8");
        // 查看该方法是否可以直接放行
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
        Pass pass = method.getAnnotation(Pass.class);
        //如果有该注解则直接放行
        if(pass!=null){
            return true;
        }
        HttpSession session = request.getSession();

       // 后台用户都要已admin开头,前台用户要以/front 开头
        String uri = request.getRequestURI();
        String uriAuth = null;
        String ajax = request.getHeader("X-Requested-With");
        uri = uri.replace(request.getContextPath(), "");

        // 获取全局权限表
        if (uri.startsWith("/")) {
            uriAuth = uri.substring(1);
        }

        SysUser user = UserUtils.getUser(session);
        if(user==null){
            // 管理员未登录
            if ("XMLHttpRequest".equals(ajax)) {
                response.setHeader("Content-Type", "application/json");
                DataRes error = DataRes.error(ResponseCode.TIMEOUT.code(), ResponseCode.TIMEOUT.desc());
                error.setData(new Page());
                ObjectMapper objectMapper=new ObjectMapper();
                response.getWriter().print(objectMapper.writeValueAsString(error));
            } else {
                String url = "<script>if (window.parent != null) {window.parent.location.href ='" +
                        request.getContextPath() + "/login/gotoLogin';} else { window.location.href ='" + request.getContextPath() + "/login/gotoLogin';}  </script>";
                response.getWriter().print(url);
            }
            return false;
        }else {
            if(admin.equals(user.getLoginName())){
                //如果是超级管理员直接放行
                return true;
            }
            Map<String, SysAuth> userAuth = (Map<String, SysAuth>) session.getAttribute(SessionConstant.userAuth);
            if(userAuth.get(uriAuth)!=null){
                return true;
            }

            //判断是否有标识权限注解
            Auth auth = method.getAnnotation(Auth.class);
            if(auth!=null){
                if (auth.value().length==0){
                    return true;
                }
                for (String uris : auth.value()) {
                    if(userAuth.get(uris)!=null){
                        return true;
                    }
                }
            }

            if ("XMLHttpRequest".equals(ajax)) {
                response.setHeader("Content-Type", "application/json");
                DataRes error = DataRes.error(ResponseCode.ATUH.code(), ResponseCode.ATUH.desc());
                ObjectMapper objectMapper=new ObjectMapper();
                response.getWriter().print(objectMapper.writeValueAsString(error));
            }else {
                response.getWriter().print("<script>window.location.href ='"+request.getContextPath()+"/error/goto403'</script>");
            }
            return false;
        }
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
