package top.senseiliu.everything.kaleido.provider.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Slf4j
@Configuration
public class WebConfig implements WebMvcConfigurer {

    /**
     * 添加拦截器
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        TraceIdTraceInterceptor traceIdTraceInterceptor = new TraceIdTraceInterceptor();
        registry.addInterceptor(traceIdTraceInterceptor);
    }
    
}
