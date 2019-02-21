package com.sb.ed.util;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.StringWriter;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;


/**
 * @description：json工具类 功能详细描述：提供bean与json之间的转换和list与json之间的转换
 * @version 1.0
 * @author: Yang.Chang
 * @email: cy880708@163.com
 * @date: 2018/12/4 下午2:12
 * @mofified By:
 */
public class JacksonMapperUtil {

    /** 记录日志的变量 */
    private static final Logger logger        = LoggerFactory.getLogger(JacksonMapperUtil.class);
    /** 静态ObjectMapper */
    private ObjectMapper mapper;

    private static JacksonMapperUtil jacksonMapper = new JacksonMapperUtil();

    private static final int         ARRAY_MAX     = 1024;

    /**
     * 私有构造函数
     */
    private JacksonMapperUtil() {
        mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    /**
     * 获得ObjectMapper实例
     * @return ObjectMapper
     */
    public static ObjectMapper getInstance() {
        return jacksonMapper.mapper;
    }

    /**
     * JSON对象转换为JavaBean
     * @param json JSON对象
     * @param valueType Bean类
     * @return 泛型对象
     */
    public static <T> T jsonToBean(String json,
                                   Class<T> valueType) {
        if (json == null || json.length() == 0) {
            return null;
        }
        try {
            return getInstance().readValue(json, valueType);
        } catch (JsonParseException e) {
            logger.error(e.getMessage(), e);
        } catch (JsonMappingException e) {
            logger.error(e.getMessage(), e);
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        }
        return null;
    }

    /**
     * JavaBean转换为JSON字符串
     * @param bean JavaBean对象
     * @return json字符串
     */
    public static String beanToJson(Object bean) {
        StringWriter sw = new StringWriter();
        JsonGenerator gen = null;
        try {
            gen = new JsonFactory().createJsonGenerator(sw);
            getInstance().writeValue(gen, bean);
            gen.close();
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        }

        return sw.toString();
    }

    /**
     * 功能描述: <br>
     * @param <T>
     * @param json
     * @param clazz
     * @return
     * @throws IOException
     * @throws JsonMappingException
     * @throws JsonParseException
     */
    @SuppressWarnings("unchecked")
    public static <T> List<T> jsonToList(String json,
                                         Class<T> clazz) {
        T[] t = (T[]) Array.newInstance(clazz, ARRAY_MAX);
        try {
            t = (T[]) getInstance().readValue(json, t.getClass());
            return (List<T>) Arrays.asList(t);
        } catch (JsonGenerationException e) {
            logger.error(e.getMessage(), e);
        } catch (JsonMappingException e) {
            logger.error(e.getMessage(), e);
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        }
        return null;
    }

    /**
     * 功能描述: <br>
     * @param t
     * @return
     */
    public static String listToJson(List<?> t) {
        try {
            return getInstance().writeValueAsString(t);
        } catch (JsonGenerationException e) {
            logger.error(e.getMessage(), e);
        } catch (JsonMappingException e) {
            logger.error(e.getMessage(), e);
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        }
        return null;
    }

}
