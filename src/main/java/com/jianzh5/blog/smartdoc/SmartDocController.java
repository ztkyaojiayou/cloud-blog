package com.jianzh5.blog.smartdoc;

import com.jianzh5.blog.valid.ValidGroup;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.groups.Default;

/**
 * Smart-Doc 功能测试
 * @author jam
 * @date 2021/9/10 5:14 下午
 */
@RestController
@RequestMapping("smart")
@Slf4j
public class SmartDocController {

    /**
     * 简单Get接口
     * @param userName 用户
     * @return 信息
     */
    @RequestMapping("/say")
    public String sayHello(String userName){
        return "hello："+userName+",欢迎使用 smart-doc";
    }


    /**
     * 参数校验分组 - 新增
     * @param smartDTO 校验
     * @return 响应结果
     */
    @PostMapping(value = "/add")
    public String add(@Validated(value = {ValidGroup.Crud.Create.class, Default.class}) SmartDTO smartDTO){
        log.info("validEntity is {}", smartDTO);
        return "test3 valid success";
    }


    /**
     * 参数校验分组 - 编辑
     * @param smartDTO
     * @return
     */
    @PutMapping(value = "/update")
    public String update(@Validated(value = {ValidGroup.Crud.Update.class, Default.class}) SmartDTO smartDTO){
        log.info("validEntity is {}", smartDTO);
        return "test4 valid success";
    }



}
