package com.sb.ed.intercepter;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @version 1.0
 * @description：
 * @author: Yang.Chang
 * @project: cloud-general
 * @package: com.bdjr.client.app.intercepter、
 * @email: cy880708@163.com
 * @date: 2019/2/21 上午11:00
 * @mofified By:
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    /**
     * @description：配置拦截器
     * @version 1.0
     * @author: Yang.Chang
     * @email: cy880708@163.com
     * @date: 2019/2/21 上午11:00
     * @mofified By:
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 添加拦截器
        registry.addInterceptor(new AppInterceptor()).addPathPatterns("/**");
    }
}
