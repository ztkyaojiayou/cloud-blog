package com.jianzh5.blog.valid;

import org.hibernate.validator.HibernateValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

/**
 * @author jam
 * @date 2021/7/30 10:52 下午
 */
@Configuration
public class ValidatedConfig {

    /**
     * 配置快速失败
     * @return
     */
    @Bean
    public Validator validator(){
        ValidatorFactory validatorFactory = Validation.byProvider(HibernateValidator.class)
                .configure()
                .failFast(true)
                .buildValidatorFactory();

        return validatorFactory.getValidator();
    }
}
