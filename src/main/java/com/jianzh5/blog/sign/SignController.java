package com.jianzh5.blog.sign;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author jam
 * 公众号：JAVA日知录
 * @date 2023/1/12 10:39
 */
@RestController
@Slf4j
public class SignController {
    /**
     * 测试需要签名的get请求
     */
    @GetMapping("/sign/testGet")
    public SignDTO testGet(String key, String value, String type) {
        SignDTO signDTO=new SignDTO();
        signDTO.setKey(key);
        signDTO.setType(type);
        signDTO.setValue(value);
        return  signDTO;
    }
    /**
     * 测试需要签名的post请求
     */
    @PostMapping(value="/sign/testPost")
    public SignDTO  testPost( @RequestBody SignDTO signDTO ){
        signDTO.setType("POST");
        return signDTO;
    }
}
