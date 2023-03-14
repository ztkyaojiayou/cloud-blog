package com.jianzh5.blog.sign;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

/**
 * @author jam
 * 公众号：JAVA日知录
 * @date 2023/1/13 09:38
 */
@UtilityClass
@Slf4j
public class JsonUtil {
    private static ObjectMapper mapper = new ObjectMapper();

    static {
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        //设置输入时忽略JSON字符串中存在而Java对象实际没有的属性
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    /**
     * 对象转Json字符串
     */
    public static String object2Json(Object o) {
        if (o == null) {
            return null;
        }
        String s = null;
        try {
            s = mapper.writeValueAsString(o);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return s;
    }

    /**
     * json转类对象
     */
    public <T> T json2Object(String json, Class<T> c) {
        if (!StringUtils.hasLength(json)) {
            return null;
        }

        T t = null;
        try {
            t = mapper.readValue(json, c);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return t;
    }

    /**
     * json转TypeReference对象
     */
    public <T> T json2Object(String json, TypeReference<T> tr) {
        if (!StringUtils.hasLength(json)) {
            return null;
        }

        T t = null;
        try {
            t = mapper.readValue(json, tr);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return t;
    }

}
