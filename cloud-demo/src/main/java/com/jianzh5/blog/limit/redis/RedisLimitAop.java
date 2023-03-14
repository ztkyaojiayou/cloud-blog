package com.jianzh5.blog.limit.redis;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.scripting.support.ResourceScriptSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * Limit AOP
 * @author 公众号：java日知录
 * @date 2021/9/24 3:07 下午
 */
@Slf4j
@Aspect
@Component
public class RedisLimitAop {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;


    private DefaultRedisScript<Long> redisScript;

    @PostConstruct
    public void init(){
        redisScript = new DefaultRedisScript<>();
        redisScript.setResultType(Long.class);
        redisScript.setScriptSource(new ResourceScriptSource(new ClassPathResource("rateLimiter.lua")));
    }

    @Pointcut("@annotation(com.jianzh5.blog.limit.redis.RedisLimit)")
    private void check() {

    }

    @Before("check()")
    public void before(JoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();

        //拿到RedisLimit注解，如果存在则说明需要限流
        RedisLimit redisLimit = method.getAnnotation(RedisLimit.class);

        if(redisLimit != null){
            //获取redis的key
            String key  = redisLimit.key();
            String className = method.getDeclaringClass().getName();

            String limitKey = key + ":" + className + ":" + method.getName();

            log.info(limitKey);

            if(StringUtils.isEmpty(key)){
                throw new RedisLimitException( "key cannot be null" );
            }

            long limit = redisLimit.permitsPerSecond();

            long expire = redisLimit.expire();

            List<String> keys = new ArrayList<>();
            keys.add( key );

           // String luaScript = buildLuaScript();
            //RedisScript<Long> redisScript = new DefaultRedisScript<>( luaScript, Long.class );

            Long count = stringRedisTemplate.execute( redisScript, keys, String.valueOf(limit), String.valueOf(expire) );

            log.info( "Access try count is {} for key={}", count, key );

            if (count != null && count == 0) {
                log.debug("令牌桶={}，获取令牌失败",key);
                throw new RedisLimitException(redisLimit.msg());
               // this.responseFail(redisLimit.msg());
            }
        }

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
        luaString.append( "\nlocal curentLimit = tonumber(redis.call('get', key) or \"0\")" );
        luaString.append( "\nif curentLimit + 1 > limit then" );
        luaString.append( "\nreturn 0" );
        luaString.append( "\nelse" );
        //自增长 1
        luaString.append( "\n redis.call(\"INCRBY\", key, 1)" );
        //设置过期时间
        luaString.append( "\nredis.call(\"EXPIRE\", key, ARGV[2])" );
        luaString.append( "\nreturn curentLimit + 1" );
        luaString.append( "\nend" );
        return luaString.toString();
    }
}
