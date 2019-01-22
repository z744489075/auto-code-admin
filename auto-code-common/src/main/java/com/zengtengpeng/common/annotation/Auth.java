package com.zengtengpeng.common.annotation;

import java.lang.annotation.*;

/**
 * 授权 权限标识. 默认为url
 */
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Auth {
    /**
     * 当controller映射多个url 或者 多个方法要使用改权限时,可以将权限url加进来,
     * 如果什么都不填则表示所有的登录用户都有该权限
     * @return
     */
    String[] value() default {};

}
