package com.sb.ed.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @version 1.0
 * @description：
 * @author: Yang.Chang
 * @project: spring-boot-encrypt-decode
 * @package: com.sb.ed.controller、
 * @email: cy880708@163.com
 * @date: 2019/2/21 下午2:12
 * @mofified By:
 */
@RestController
public class IndexController {

    private static final Log logger = LogFactory.getLog(IndexController.class);

    @RequestMapping(value = "/index", method = RequestMethod.POST)
    public String index(@RequestBody() String val){
        logger.info("请求信息： " + val);
        return val;
    }
}
