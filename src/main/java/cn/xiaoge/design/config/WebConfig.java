package cn.xiaoge.design.config;
import cn.xiaoge.design.interceptor.FirstInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.session.web.http.SessionEventHttpSessionListenerAdapter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import javax.servlet.http.HttpSessionListener;
import java.util.ArrayList;
import java.util.List;


@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(getFirstInterceptor()).addPathPatterns("/boxType/*", "/material/*", "/purpose/*", "/alcoholTemplate/*", "/order/*", "/appUser/*", "/appVersion/*", "/systemUser/*");
    }

    @Bean
    public FirstInterceptor getFirstInterceptor() {
        return new FirstInterceptor();
    }

    @Bean
    public SessionEventHttpSessionListenerAdapter getSessionEventHttpSessionListenerAdapter() {
        List<HttpSessionListener> listeners = new ArrayList<>();
        listeners.add(getHttpSessionListener());
        return new SessionEventHttpSessionListenerAdapter(listeners);
    }

    @Bean
    public HttpSessionListener getHttpSessionListener() {
        return new MySessionListener();
    }


}
