package com.jianzh5.limit.manager;

import com.google.common.collect.Maps;
import com.google.common.util.concurrent.RateLimiter;
import com.jianzh5.limit.exception.LimiterException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author jam
 * 公众号：JAVA日知录
 * @date 2023/3/14 19:42
 * Guava限流实现类
 */
@Slf4j
public class GuavaLimiter implements LimiterManager{
    private final Map<String, RateLimiter> limiterMap = Maps.newConcurrentMap();

    @Override
    public boolean tryAccess(Limiter limiter) {
        RateLimiter rateLimiter = getRateLimiter(limiter);
        if (rateLimiter == null) {
            return false;
        }

        boolean access = rateLimiter.tryAcquire(1,100, TimeUnit.MILLISECONDS);

        log.info("{} access :{}",limiter.getKey() , access);

        return access;
    }

    /**
     * 创建Guava 限流器
     * @param limiter 限流配置
     * @return RateLimiter
     */
    private RateLimiter getRateLimiter(Limiter limiter) {
        if(limiter == null){
            return null;
        }

        String key = limiter.getKey();
        if(StringUtils.isEmpty(key)){
            throw new LimiterException( "guava limiter key cannot be null" );
        }

        RateLimiter rateLimiter = limiterMap.get( key );

        if(rateLimiter == null){
            int seconds = limiter.getSeconds();
            double limitNum = limiter.getLimitNum();
            double permitsPerSecond = limitNum / seconds;
            RateLimiter newRateLimiter = RateLimiter.create(permitsPerSecond);

            rateLimiter = limiterMap.putIfAbsent(key, newRateLimiter);

            //解决rateLimiter第一次为null的问题
            if (rateLimiter == null) {
                rateLimiter = newRateLimiter;
            }

        }

        return rateLimiter;
    }

}
