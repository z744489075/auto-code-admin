package com.zengtengpeng.common.aop;

import com.alibaba.fastjson.JSON;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * 日志打印
 */
@Aspect
@Component
@Configuration
public class LogAop {
    private Logger log = LoggerFactory.getLogger(getClass());

    @Pointcut("!execution( * com.zengtengpeng.ui.controller.*.*(..)) && execution( * com.zengtengpeng.*.controller.*.*(..))")
    public void controllerAspect() {
    }

    @Around("controllerAspect()")
    public Object aroundAdvice(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        long time = System.currentTimeMillis();

        //执行aop
        Object obj = proceedingJoinPoint.proceed();

        //执行完毕后打印日志, 这样日志就不会分开
        Object[] args = proceedingJoinPoint.getArgs();
        List param = new ArrayList();
        for (Object arg : args) {
            if (arg instanceof HttpServletRequest || arg instanceof HttpServletResponse) {
            } else {
                param.add(arg);
            }
        }
        String id = UUID.randomUUID().toString().replaceAll("-", "");
        String req = JSON.toJSONString(param);
        log.info("-------------------------------------------请求内容-> {} -------------------------------------------", id);
        log.info("请求类方法{}", proceedingJoinPoint.getSignature());
        log.info("请求类方法参数:{}", req);
        String res = JSON.toJSONString(obj);
        log.info("-------------------------------------------返回内容-> {} -------------------------------------------",id);
        log.info("返回时间:" + (System.currentTimeMillis() - time) + "/sm");
        log.info("Response内容:" + res);
        log.info("-------------------------------------------返回结束-> {} -------------------------------------------",id);
        return obj;

    }



}
