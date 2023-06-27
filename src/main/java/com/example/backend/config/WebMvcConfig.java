package com.example.backend.config;

import com.example.backend.interceptor.UserLoginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.LinkedList;
import java.util.List;

@Configuration
@ComponentScan(basePackages = "com.example.backend") //全局异常处理类需要被扫描才能
public class WebMvcConfig implements WebMvcConfigurer {

    @Autowired
    private UserLoginInterceptor userLoginInterceptor;
    /**
     * 注册自定义拦截器
     *
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        List<String> urls = new LinkedList<>();
        urls.add("/favicon.ico");
        urls.add("/error");
        urls.add("/swagger-resources/**");
        urls.add("/webjars/**");
        urls.add("/v2/**");
        urls.add("/doc.html");
        urls.add("**/swagger-ui.html");
        urls.add("/swagger-ui.html/**");
        // urls.add("/user/login");

        registry.addInterceptor(userLoginInterceptor)
                .addPathPatterns("/**")
                //.addPathPatterns("/user/**")
                //.addPathPatterns("/userInfo/**")
                .excludePathPatterns(urls);//开放登录路径
    }

}
