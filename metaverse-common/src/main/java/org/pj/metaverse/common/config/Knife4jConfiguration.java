package org.pj.metaverse.common.config;

import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j;
import io.swagger.annotations.Api;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.Collections;
import java.util.List;

/**
 * @author pengjie
 * @date 14:27 2022/5/18
 **/
@Configuration
@EnableKnife4j
public class Knife4jConfiguration {

    @Value("${swagger3.enable}")
    private Boolean enableSwagger;
    @Value("${swagger3.title}")
    private String title;
    @Value("${swagger3.description}")
    private String description;
    @Value("${swagger3.authHeaderKey}")
    private String authHeaderKey;

    @Bean
    public Docket defaultApi3() {
        return new Docket(DocumentationType.OAS_30)
                .enable(enableSwagger)
                .apiInfo(this.apiInfo())
                //分组名称
                .groupName("1.0")
                .select()
                //这里指定Controller扫描包路径
                .apis(RequestHandlerSelectors.withClassAnnotation(Api.class))
                .paths(PathSelectors.any())
                .build()
                // 设置安全认证信息
                .securitySchemes(securitySchemes())
                // 设置安全认证应用范围
                .securityContexts(securityContexts());
    }
    /**
     * Swagger UI 头部信息
     *
     * @return
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title(title)
                .description(description)
                .contact(new Contact("Charlotte","xxxx","492931993@qq.com"))
                .build();
    }

    /**
     * 权限认证信息(token)
     *
     * @return
     */
    private List<SecurityScheme> securitySchemes() {
        ApiKey apiKey = new ApiKey(authHeaderKey, authHeaderKey, In.HEADER.toValue());
        return Collections.singletonList(apiKey);
    }

    /**
     * 设置权限认证应用范围
     */
    private List<SecurityContext> securityContexts() {
        return Collections.singletonList(
                SecurityContext.builder()
                        .securityReferences(Collections.singletonList(new SecurityReference(authHeaderKey,
                                new AuthorizationScope[]{new AuthorizationScope("global", "")})))
                        .build()
        );
    }
}
