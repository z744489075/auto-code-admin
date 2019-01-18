package com.zengtengpeng.common.annotation;

/**
 * 授权 权限标识. 默认为url
 */
public @interface Auth {
    /**
     * 当controller映射多个url 或者 多个方法要使用改权限时,可以将权限url加进来
     * @return
     */
    String value() default "";
}
