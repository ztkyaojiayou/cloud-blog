package com.jianzh5.blog.limit;


import com.jianzh5.limit.aop.Limit;
import com.jianzh5.limit.manager.LimiterManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author jam
 * 公众号：JAVA日知录
 * @date 2023/3/16 18:27
 */
@RestController
@RequestMapping("/limiter")
@Slf4j
public class LimiterController {

    @Autowired
    private ApplicationContext applicationContext;

    /**
     * 基于AOP限流1
     */
    @GetMapping("/test")
    @Limit(key = "Limiter:test",limitNum = 3,seconds = 1)
    public String limit() {
        LimiterManager bean = applicationContext.getBean(LimiterManager.class);
        log.info("Limiter:test获取令牌成功，当前限流器:{}",bean.getClass().getName());
        return "ok";
    }
}
