package cn.chenyuxian.learnjava.config;

import java.util.Collections;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.annotations.ApiOperation;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.Contact;
import springfox.documentation.service.HttpAuthenticationScheme;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
@ConditionalOnProperty(value = "springfox.documentation.enabled", havingValue = "true", matchIfMissing = true)
public class SwaggerConfig {
	
	@Bean
	public Docket createRestApi() {
		return new Docket(DocumentationType.OAS_30)
				.apiInfo(apiInfo())
				.securitySchemes(Collections.singletonList(HttpAuthenticationScheme.JWT_BEARER_BUILDER
						.name("JWT")
						.build()))
				.securityContexts(Collections.singletonList(SecurityContext.builder()
						.securityReferences(Collections.singletonList(SecurityReference.builder()
								.scopes(new AuthorizationScope[0])
								.reference("JWT")
								.build()))
						.operationSelector(o -> o.requestMappingPattern().matches("/.*"))
						.build()))
				.select()
				.apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
				.paths(PathSelectors.any())
				.build();
	}
	
	private ApiInfo apiInfo() {
		return new ApiInfoBuilder()
				.title("Swagger3接口文档")
				.description("更多请咨询yxcling.cn")
				.contact(new Contact("陈育先", "https://www.yxcling.cn", "2586846075@qq.com"))
				.version("1.0")
				.build();
	}
}
