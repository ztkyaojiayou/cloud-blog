package com.jianzh5.blog.limit;

import com.google.common.util.concurrent.RateLimiter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;

/**
 * 限流测试类基于Guava 令牌限流
 * @author javadaily
 * @date 2021/9/24 11:50 上午
 */
@Slf4j
@RestController
@RequestMapping("/limit")
public class LimitController {
    /**
     * 限流策略 ： 1秒钟2个请求
     */
    private final RateLimiter limiter = RateLimiter.create(2.0);

    private DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    /**
     * 手动限流
     */
    @GetMapping("/test1")
    public String testLimiter() {
        //500毫秒内，没拿到令牌，就直接进入服务降级
        boolean tryAcquire = limiter.tryAcquire(500, TimeUnit.MILLISECONDS);

        if (!tryAcquire) {
            log.warn("进入服务降级，时间{}", LocalDateTime.now().format(dtf));
            return "当前排队人数较多，请稍后再试！";
        }

        log.info("获取令牌成功，时间{}", LocalDateTime.now().format(dtf));
        return "请求成功";
    }

    /**
     * 基于AOP限流1
     */
    @GetMapping("/test2")
    @Limit(key = "limit2", permitsPerSecond = 1, timeout = 500, timeunit = TimeUnit.MILLISECONDS,msg = "当前排队人数较多，请稍后再试！")
    public String limit2() {
        log.info("令牌桶limit2获取令牌成功");
        return "ok";
    }

    /**
     * 基于AOP限流2
     */
    @GetMapping("/test3")
    @Limit(key = "limit3", permitsPerSecond = 2, timeout = 500, timeunit = TimeUnit.MILLISECONDS,msg = "系统繁忙，请稍后再试！")
    public String limit3() {
        log.info("令牌桶limit3获取令牌成功");
        return "ok";
    }


}
