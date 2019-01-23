package com.zengtengpeng.interceptor;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zengtengpeng.common.annotation.Auth;
import com.zengtengpeng.sys.bean.SysAuth;
import com.zengtengpeng.sys.bean.SysOperationLog;
import com.zengtengpeng.sys.bean.SysUser;
import com.zengtengpeng.sys.constant.SessionConstant;
import com.zengtengpeng.sys.service.SysOperationLogService;
import com.zengtengpeng.utils.RequestUtils;
import com.zengtengpeng.utils.UserUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.resource.ResourceHttpRequestHandler;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.lang.reflect.Method;
import java.util.Map;

/**
 * 日志拦截器
 */
public class LogInterceptor implements HandlerInterceptor {

   public static ThreadLocal<SysOperationLog> threadLocal=new ThreadLocal<>();

   @Resource
   private SysOperationLogService sysOperationLogService;

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

        SysUser user = UserUtils.getUser(request);
        if(user!=null){
            HttpSession session = request.getSession();

            String uri = request.getRequestURI();
            uri = uri.replace(request.getContextPath(), "");
            // 去除根路径
            if (uri.startsWith("/")) {
                uri = uri.substring(1);
            }

            Map<String, SysAuth> href = (Map<String, SysAuth>) session.getAttribute(SessionConstant.authAllByHref);
            Map<Integer, SysAuth> sysAuthMap = (Map<Integer, SysAuth>) session.getAttribute(SessionConstant.authAllById);

            //查找是否有权限
            SysAuth sysAuth= href.get(uri);
            Auth auth = method.getAnnotation(Auth.class);
            if(sysAuth==null&&auth!=null&&auth.value().length>0){
                for (String s : auth.value()) {
                    sysAuth= href.get(s);
                    if (sysAuth!=null){
                        break;
                    }
                }

            }
            //只有当权限存在的情况才记录
            if(sysAuth!=null){
                SysOperationLog sysOperationLog=new SysOperationLog();
                sysOperationLog.setSysUserId(user.getId());
                sysOperationLog.setSysAuthId(sysAuth.getId());
                StringBuilder stringBuilder=new StringBuilder();
                getAuthName(sysAuth,sysAuthMap,stringBuilder);
                sysOperationLog.setAuthName(stringBuilder.substring(0,stringBuilder.length()-2));

                sysOperationLog.setIp(RequestUtils.getIp(request));

                ObjectMapper objectMapper=new ObjectMapper();
                sysOperationLog.setRequestParam(objectMapper.writeValueAsString(request.getParameterMap()));
                sysOperationLog.setAuthHref(uri);
                threadLocal.set(sysOperationLog);
                sysOperationLogService.insert(sysOperationLog);
            }

        }
       return true;
    }

    private void getAuthName(SysAuth sysAuth, Map<Integer, SysAuth> sysAuthMap,StringBuilder sb){
        sb.insert(0,sysAuth.getName()+"->");
        if (sysAuth.getParentAuthId()!=null&&!sysAuth.getParentAuthId().equals(0)){
            getAuthName(sysAuthMap.get(sysAuth.getParentAuthId()),sysAuthMap,sb);
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
