package com.sb.ed.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

/**
 * @version 1.0
 * @description：
 * @author: Yang.Chang
 * @project: spring-boot-encrypt-decode
 * @package: com.sb.ed.util、
 * @email: cy880708@163.com
 * @date: 2019/2/21 下午4:55
 * @mofified By:
 */
public class test {
    public static void main(String[] args) {
        String s = "%7BrequestData%3A%22%E5%A5%BD%E5%90%A7%E5%A5%BD%E5%90%A7%22%7D=";
        String keyWord = null;
        try {
            keyWord = URLDecoder.decode(s,"utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        System.out.println(keyWord);
    }
}
