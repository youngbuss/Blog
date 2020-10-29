package com.yang.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration

public class MyMvcConfig implements WebMvcConfigurer {
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/admin").setViewName("admin/login");
        registry.addViewController("/admin/login.html").setViewName("admin/login");
        registry.addViewController("/admin/main").setViewName("admin/blogs");
        registry.addViewController("/").setViewName("index");

    }

  /*  @Bean
    public LocaleResolver localeResolver(){
        return new MyLocaleResolver();
    }*/

    public void addInterceptors(InterceptorRegistry registry){
        registry.addInterceptor(new LoginHandlerInterceptor()).addPathPatterns("/**").excludePathPatterns("/","/blog/**","/index.html","/admin/login.html","/admin","/admin/tologin","/admin/login","/css/**","/js/**","/image/**","/lib/**");
    }
}
