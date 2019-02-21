package com.sb.ed.intercepter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @version 1.0
 * @description：
 * @author: Yang.Chang
 * @project: cloud-general
 * @package: com.bdjr.client.app.intercepter、
 * @email: cy880708@163.com
 * @date: 2019/2/21 上午10:59
 * @mofified By:
 */
@Component
public class AppInterceptor implements HandlerInterceptor {

    private static final Logger logger = LoggerFactory.getLogger(AppInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        //打印请求数据
        Map parameterMap = request.getParameterMap();
        if(parameterMap.get("value") != null) {
            logger.info("请求的数据为 : " +parameterMap.get("value").toString());
        }

        //打印请求地址
        String url = request.getRequestURI();
        logger.info("url : " + url);
        return true;
    }
}
