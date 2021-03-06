package com.sb.ed.intercepter;

import com.sb.ed.config.SecurityParameter;
import com.sb.ed.util.DESHelper;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.RequestBodyAdvice;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.URLDecoder;
import java.nio.charset.Charset;

/**
 * @version 1.0
 * @description：请求数据解密
 * @author: Yang.Chang
 * @project: spring-boot-encrypt-decode
 * @package: com.sb.ed.intercepter、
 * @email: cy880708@163.com
 * @date: 2019/2/21 下午1:58
 * @mofified By:
 */
@ControllerAdvice(basePackages = "com.sb.ed")
public class MyRequestBodyAdvice implements RequestBodyAdvice {

    private static final Logger logger = LoggerFactory.getLogger(MyRequestBodyAdvice.class);

    private static final Charset DEFAULT_CHARSET = Charset.forName("UTF-8");

    //是否解密
    private static boolean ENCODE = true;

    @Override
    public boolean supports(MethodParameter methodParameter, Type type, Class<? extends HttpMessageConverter<?>> aClass) {
        return true;
    }

    @Override
    public Object handleEmptyBody(Object body, HttpInputMessage httpInputMessage, MethodParameter methodParameter, Type type, Class<? extends HttpMessageConverter<?>> aClass) {
        return body;
    }

    @Override
    public HttpInputMessage beforeBodyRead(HttpInputMessage inputMessage, MethodParameter methodParameter, Type type, Class<? extends HttpMessageConverter<?>> aClass) throws IOException {
        try {
            if (methodParameter.getMethod().isAnnotationPresent(SecurityParameter.class)) {
                //获取注解配置的包含和去除字段
                SecurityParameter serializedField = methodParameter.getMethodAnnotation(SecurityParameter.class);
                //入参是否需要解密
                ENCODE = serializedField.inDecode();
            }
            logger.info("对方法method :【" + methodParameter.getMethod().getName() + "】返回数据进行解密");
            return new MyHttpInputMessage(inputMessage);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("对方法method :【" + methodParameter.getMethod().getName() + "】返回数据进行解密出现异常："+e.getMessage());
            return inputMessage;
        }
    }

    @Override
    public Object afterBodyRead(Object body, HttpInputMessage httpInputMessage, MethodParameter methodParameter, Type type, Class<? extends HttpMessageConverter<?>> aClass) {
        return body;
    }

    class MyHttpInputMessage implements HttpInputMessage {

        private HttpHeaders headers;

        private InputStream body;

        public MyHttpInputMessage(HttpInputMessage inputMessage) throws Exception {
            this.headers = inputMessage.getHeaders();
            String bodyMessage = FileCopyUtils.copyToString(new InputStreamReader(inputMessage.getBody(), "utf-8"));
            if (ENCODE) {
                this.body = IOUtils.toInputStream(DESHelper.decrypt(easpString(URLDecoder.decode(bodyMessage, "utf-8"))));
            } else {
                this.body = IOUtils.toInputStream(easpString(URLDecoder.decode(bodyMessage, "utf-8")));
            }
        }

        private Charset getContentTypeCharset(MediaType contentType) {
            if (contentType != null && contentType.getCharset() != null) {
                return contentType.getCharset();
            } else {
                return DEFAULT_CHARSET;
            }
        }

        @Override
        public InputStream getBody() throws IOException {
            return body;
        }

        @Override
        public HttpHeaders getHeaders() {
            return headers;
        }

        /**
         * @description：迭代格式化加密JSON串
         * @version 1.0
         * @author: Yang.Chang
         * @email: cy880708@163.com
         * @date: 2019/2/21 下午6:28
         * @mofified By:
         */
        public String easpString(String requestData){
            if(requestData != null && !requestData.equals("")){
                if(!requestData.startsWith("value=")){
                    throw new RuntimeException("参数【requestData】缺失异常！");
                }else{
                    int closeLen = requestData.length();
                    int openLen = "value=".length();
                    return StringUtils.substring(requestData,openLen,closeLen);
                }
            }
            return "";
        }
    }
}

