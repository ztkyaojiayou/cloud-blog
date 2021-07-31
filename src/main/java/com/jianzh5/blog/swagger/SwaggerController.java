package com.jianzh5.blog.swagger;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author jam
 * @date 2021/7/31 9:10 下午
 */
@RestController
@Slf4j
@RequestMapping("/swagger")
@Api(tags = "Swagger接口文档")
public class SwaggerController {

    @ApiOperation(value = "say",response = String.class)
    @GetMapping("/say")
    public String say(){
        return "Hello,Swagger";
    }



}
