package com.zengtengpeng.common.annotation;

import java.lang.annotation.*;

/**
 * 权限注解, 当标注上为直接放行
 * zengtengpeng
 *
 * @create 2016-09-05 14:06
 **/
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Pass {
}
