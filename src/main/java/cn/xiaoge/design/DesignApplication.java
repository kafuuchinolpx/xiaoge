package cn.xiaoge.design;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


/**
 * @author 19070
 */
@SpringBootApplication
//session24*60秒过期
@EnableRedisHttpSession(maxInactiveIntervalInSeconds = 3600 * 24)
@EnableSwagger2
public class DesignApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(DesignApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(DesignApplication.class);
    }
}