package com.jianzh5.limit.manager;

import com.jianzh5.limit.exception.LimiterException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author jam
 * 公众号：JAVA日知录
 * @date 2023/3/14 19:48
 * Redis限流实现类
 */
@Slf4j
public class RedisLimiter implements LimiterManager{

    private final StringRedisTemplate stringRedisTemplate;

    public RedisLimiter(StringRedisTemplate stringRedisTemplate) {
        this.stringRedisTemplate = stringRedisTemplate;
    }

    @Override
    public boolean tryAccess(Limiter limiter) {

        String key = limiter.getKey();
        if (StringUtils.isEmpty(key)) {
            throw new LimiterException( "redis limiter key cannot be null" );
        }

        List<String> keys = new ArrayList<>();
        keys.add( key );

        int seconds = limiter.getSeconds();
        int limitCount = limiter.getLimitNum();

        String luaScript = buildLuaScript();

        RedisScript<Long> redisScript = new DefaultRedisScript<>(luaScript, Long.class);

        Long count = stringRedisTemplate.execute( redisScript, keys, "" + limitCount, "" + seconds );

        log.info( "Access try count is {} for key={}", count, key );

        return count != null && count != 0;
    }

    /**
     * 构建redis lua脚本
     * @return
     */
    private String buildLuaScript() {
        StringBuilder luaString = new StringBuilder();
        luaString.append( "local key = KEYS[1]" );
        //获取ARGV内参数Limit
        luaString.append( "\nlocal limit = tonumber(ARGV[1])" );
        //获取key的次数
        luaString.append( "\nlocal currentLimit = tonumber(redis.call('get', key) or \"0\")" );
        luaString.append( "\nif currentLimit + 1 > limit then" );
        luaString.append( "\nreturn 0" );
        luaString.append( "\nelse" );
        //自增长 1
        luaString.append( "\n redis.call(\"INCRBY\", key, 1)" );
        //设置过期时间
        luaString.append( "\nredis.call(\"EXPIRE\", key, ARGV[2])" );
        luaString.append( "\nreturn currentLimit + 1" );
        luaString.append( "\nend" );
        return luaString.toString();
    }


}
