package com.zengtengpeng.common.annotation;

import java.lang.annotation.*;

/**
 * 权限注解, 当标注上为直接放行
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Pass {
}
