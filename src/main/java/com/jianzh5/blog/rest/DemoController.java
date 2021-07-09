package com.jianzh5.blog.rest;

import com.jianzh5.blog.map.Aniaml;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author jam
 * @date 2021/7/8 10:00 上午
 */
@RestController
@Slf4j
public class DemoController {

    @GetMapping("/hello")
    public String getStr(){
        return "hello,javadaily";
    }

    @GetMapping("/aniaml")
    public Aniaml getAniaml(){
        Aniaml aniaml = new Aniaml(1,"pig");
        return aniaml;
    }

    @GetMapping("/wrong")
    public int error(){
        int i;
        try{
            i = 9/0;
        }catch (Exception e){
            log.error("error:{}",e);
            i = 0;
        }
        return i;
    }

    @GetMapping("error1")
    public void empty(){
        throw  new RuntimeException("自定义异常");
    }

}
