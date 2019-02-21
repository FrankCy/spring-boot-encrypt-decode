# Spring Boot Encrypt Decode #
## 简介 ##
Spring Boot 2.0+ 请求加密解密

## RequestBodyAdvice / ResponseBodyAdvice ##
- RequestBodyAdvice
请求拦截并加密

- ResponseBodyAdvice
响应拦截并加密

- 请求必须是@RequestBody、请求的Content-Type=application/x-www-form-urlencoded
```java
public String index(@RequestBody String body){
        logger.info("请求信息： " + body);
        return body;
    }
```