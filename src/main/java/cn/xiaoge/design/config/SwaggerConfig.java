package cn.xiaoge.design.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
@Value("${enableSwagger2}")
private boolean enableSwagger2;

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .enable(enableSwagger2)
                .apiInfo(apiInfo())
                .select()
                // 选择那些路径和api会生成document
                .apis(RequestHandlerSelectors.basePackage("cn.xiaoge.design.controller"))
                // 对所有api进行监控
                //.apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                .paths(PathSelectors.any())
                // 对所有路径进行监控
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("智能设计")
                //大标题
                .version("1.0")
                //版本
                .build();
    }


}
