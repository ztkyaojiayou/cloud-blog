package com.jianzh5.limit.config;

import com.jianzh5.limit.manager.GuavaLimiter;
import com.jianzh5.limit.manager.LimiterManager;
import com.jianzh5.limit.manager.RedisLimiter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.StringRedisTemplate;

/**
 * @author jam
 * 公众号：JAVA日知录
 * @date 2023/3/14 19:32
 * 主配置类
 */
@Configuration
public class LimiterConfigure {

    @Bean
    @ConditionalOnProperty(name = "limit.type",havingValue = "local")
    public LimiterManager guavaLimiter(){
        return new GuavaLimiter();
    }


    @Bean
    @ConditionalOnProperty(name = "limit.type",havingValue = "redis")
    public LimiterManager redisLimiter(StringRedisTemplate stringRedisTemplate){
        return new RedisLimiter(stringRedisTemplate);
    }
}
