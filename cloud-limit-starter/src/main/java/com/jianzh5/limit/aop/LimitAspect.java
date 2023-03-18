package com.jianzh5.limit.aop;

import com.jianzh5.limit.condition.LimitAspectCondition;
import com.jianzh5.limit.exception.LimiterException;
import com.jianzh5.limit.manager.Limiter;
import com.jianzh5.limit.manager.LimiterManager;
import lombok.Setter;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import java.lang.reflect.Method;

/**
 * @author jam
 * 公众号：JAVA日知录
 * @date 2023/3/15 17:20
 */
@Aspect
@EnableAspectJAutoProxy(proxyTargetClass = true) //使用CGLIB代理
@Conditional(LimitAspectCondition.class)
public class LimitAspect {

    @Setter(onMethod_ = @Autowired)
    private LimiterManager limiterManager;

    @Pointcut("@annotation(com.jianzh5.limit.aop.Limit)")
    private void check() {

    }

    @Before("check()")
    public void before(JoinPoint joinPoint){
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();

        Limit limit = method.getAnnotation(Limit.class);
        if(limit != null){

            Limiter limiter = Limiter.builder().limitNum(limit.limitNum())
                    .seconds(limit.seconds())
                    .key(limit.key()).build();

            if(!limiterManager.tryAccess(limiter)){
                throw new LimiterException( "There are currently many people , please try again later!" );
            }
        }
    }
}
