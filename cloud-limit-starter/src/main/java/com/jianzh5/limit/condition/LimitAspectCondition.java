package com.jianzh5.limit.condition;

import com.jianzh5.limit.constant.ConfigConstant;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * @author jam
 * 公众号：JAVA日知录
 * 自定义条件选择器，只有在配置文件中有`limit.type`属性时才加载对应的配置类
 */
public class LimitAspectCondition implements Condition {
    @Override
    public boolean matches(ConditionContext conditionContext, AnnotatedTypeMetadata annotatedTypeMetadata) {
        //检查配置文件是否包含limit.type属性
        return conditionContext.getEnvironment().containsProperty(ConfigConstant.LIMIT_TYPE);
    }
}
