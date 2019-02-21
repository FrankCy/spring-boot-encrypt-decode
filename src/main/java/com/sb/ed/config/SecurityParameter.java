package com.sb.ed.config;

import org.springframework.web.bind.annotation.Mapping;

import java.lang.annotation.*;

/**
 * @version 1.0
 * @description：注解
 * @author: Yang.Chang
 * @project: spring-boot-encrypt-decode
 * @package: com.sb.ed.config、
 * @email: cy880708@163.com
 * @date: 2019/2/21 下午1:56
 * @mofified By:
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Mapping
@Documented
public @interface SecurityParameter {

    boolean inDecode() default true;

    boolean outEncode() default true;

}
