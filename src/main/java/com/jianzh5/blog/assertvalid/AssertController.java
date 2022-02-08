package com.jianzh5.blog.assertvalid;

import com.jianzh5.blog.seniorvalid.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author javadaily
 * @date 2022/1/9 10:18 下午
 */
@RestController
@RequestMapping("assert")
@Slf4j
public class AssertController {

    @DeleteMapping("/user/{id}")
    public void deleteUser(@PathVariable("id") String id) {
        //模拟数据库查询用户
        User user = getUserById(id);
        Assert.notNull(user, "用户不存在！");
    }

    private User getUserById(String id) {
        return null;
    }
}
