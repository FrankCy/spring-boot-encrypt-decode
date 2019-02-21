package com.sb.ed.intercepter;

import com.sb.ed.wrapper.ParameterRequestWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
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
        HashMap m = new HashMap(request.getParameterMap());
        m.put("value", "我是被修改过的");
        HttpServletRequest req = (HttpServletRequest) request;
        ParameterRequestWrapper wrapRequest = new ParameterRequestWrapper(req, m);
        request.getParameterMap();
        request = wrapRequest;

        //打印请求地址
        String url = request.getRequestURI();
        logger.info("url : " + url);
        ((ParameterRequestWrapper) request).setRequest(request);
        return true;
    }
}
