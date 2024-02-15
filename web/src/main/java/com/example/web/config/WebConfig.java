package com.example.web.config;

import jakarta.servlet.Filter;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Bean
    public FilterRegistrationBean loggingFilter(){
        FilterRegistrationBean<Filter> filterRegistrationBean = new FilterRegistrationBean<>();
        filterRegistrationBean.setFilter(new LogFilter()); //등록
        filterRegistrationBean.setOrder(1); //filter들이 다중으로 있을때 순서대로 처리
        filterRegistrationBean.addUrlPatterns("/*"); //특정 url에 filter를 건다

        return filterRegistrationBean;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) { //registry는 등록된 목록
        registry.addInterceptor(new LogInterceptor())
                .order(1)
                .addPathPatterns("/**")
                .excludePathPatterns("/css/*", "/images/*"); //정적 파일들은 interceptor을 탈 이유가 없음
    }
}
