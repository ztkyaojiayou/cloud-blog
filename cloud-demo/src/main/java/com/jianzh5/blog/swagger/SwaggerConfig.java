package com.jianzh5.blog.swagger;

import com.jianzh5.blog.swagger.plugin.*;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.annotation.Order;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.property.bean.AccessorsProvider;
import springfox.documentation.schema.property.field.FieldProvider;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.schema.EnumTypeDeterminer;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.common.SwaggerPluginSupport;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author jam
 * @date 2021/7/31 9:07 下午
 */
@Configuration
@EnableSwagger2
@ConditionalOnClass(Docket.class)
//@EnableKnife4j
public class SwaggerConfig {
    private static final String VERSION = "1.0";

    @Value("${springfox.swagger2.enabled}")
    private boolean swaggerEnabled;

    @Bean
    public Docket createRestApi(){
        return new Docket(DocumentationType.SWAGGER_2)
                .enable(swaggerEnabled)
                .groupName("SwaggerDemo")
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.withClassAnnotation(Api.class))
                .paths(PathSelectors.any())
                .build();
    }

    /**
     * 添加摘要信息
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("接口文档")
                .contact(new Contact("JAVA日知录","http://javadaily.cn","jianzh5@163.com"))
                .description("Swagger接口文档")
                .version(VERSION)
                .build();
    }




    @Bean
    @Order(SwaggerPluginSupport.SWAGGER_PLUGIN_ORDER + 1000)
    public GroupOperationModelsProviderPlugin groupOperationModelsProviderPlugin() {
        return new GroupOperationModelsProviderPlugin();
    }

    @Bean
    @Order(SwaggerPluginSupport.SWAGGER_PLUGIN_ORDER + 1000)
    public GroupModelBuilderPlugin groupModelBuilderPlugin() {
        return new GroupModelBuilderPlugin();
    }

    @Bean
    @Order(SwaggerPluginSupport.SWAGGER_PLUGIN_ORDER + 1000)
    public GroupModelPropertyBuilderPlugin groupModelPropertyBuilderPlugin() {
        return new GroupModelPropertyBuilderPlugin();
    }

    @Bean
    @Order(SwaggerPluginSupport.SWAGGER_PLUGIN_ORDER + 1000)
    public GroupExpandedParameterBuilderPlugin groupExpandedParameterBuilderPlugin() {
        return new GroupExpandedParameterBuilderPlugin();
    }

    @Bean
    @Order(SwaggerPluginSupport.SWAGGER_PLUGIN_ORDER + 1000)
    public GroupOperationBuilderPlugin groupOperationBuilderPlugin() {
        return new GroupOperationBuilderPlugin();
    }

    @Bean
    @Primary
    @Order(SwaggerPluginSupport.SWAGGER_PLUGIN_ORDER + 1000)
    public GroupModelAttributeParameterExpander groupModelAttributeParameterExpander(FieldProvider fields, AccessorsProvider accessors, EnumTypeDeterminer enumTypeDeterminer) {
        return new GroupModelAttributeParameterExpander(fields, accessors, enumTypeDeterminer);
    }


}
