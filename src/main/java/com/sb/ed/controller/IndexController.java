package com.sb.ed.controller;

import com.sb.ed.vo.CompanyUser;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.bind.annotation.*;

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
