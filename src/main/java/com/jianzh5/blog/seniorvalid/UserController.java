package com.jianzh5.blog.seniorvalid;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author jam
 * 公众号：JAVA日知录
 * @date 2022/1/12 11:14 上午
 * https://blog.csdn.net/huanghanqian/article/details/103118657
 */
@RestController
@RequestMapping("/senior/user")
@Slf4j
@Validated
public class UserController {
    @Autowired
    private UserRepository userRepository;



    @PostMapping
    public User createUser(@UniqueUser @Valid User user){
        User savedUser = userRepository.save(user);
        log.info("save user id is {}",savedUser.getId());
        return savedUser;
    }

    @SneakyThrows
    @PutMapping
    public User updateUser(@NotConflictUser @Valid @RequestBody User user){
        User editUser = userRepository.save(user);
        log.info("update user is {}",editUser);
        return editUser;
    }

}
