package com.jianzh5.blog.limit.redis;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 公众号：JAVA日知录
 * 限流测试类基于Redis限流
 * @author jam
 * @date 2022/3/26 00:01
 */
@Slf4j
@RestController
@RequestMapping("/limit/redis")
public class LimitRedisController {

    /**
     * 基于Redis AOP限流
     */
    @GetMapping("/test")
    @RedisLimit(key = "redis-limit:test", permitsPerSecond = 2, expire = 1, msg = "当前排队人数较多，请稍后再试！")
    public String test() {
        log.info("限流成功。。。");
        return "ok";
    }

}
