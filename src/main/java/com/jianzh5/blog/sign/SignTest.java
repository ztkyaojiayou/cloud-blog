package com.jianzh5.blog.sign;

import org.springframework.util.DigestUtils;

import java.util.UUID;

/**
 * @author jam
 * 公众号：JAVA日知录
 * @date 2023/1/15 14:13
 */
public class SignTest {
    public static void main(String[] args) {
        SignDTO signDTO = new SignDTO();
        signDTO.setType("attribute");
        signDTO.setKey("name");
        signDTO.setValue("javadaily");

        Long timestamp = System.currentTimeMillis() / 1000;
        System.out.println(timestamp);
        String nonce = UUID.randomUUID().toString();
        System.out.println(nonce);
        String params = nonce + timestamp + JsonUtil.object2Json(signDTO);
        System.out.println(params);
        String sign = DigestUtils.md5DigestAsHex(params.getBytes()).toUpperCase();
        System.out.println(sign);
    }
}
