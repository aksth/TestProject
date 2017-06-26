package com.test.config;

import com.test.interceptor.TokenAuthenticatorInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.http.MediaType;
import org.springframework.web.accept.ContentNegotiationManager;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.view.ContentNegotiatingViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

/**
 * @author ayush.regmi
 */
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {"com.test.controller"})
@EnableSpringDataWebSupport
public class WebConfig extends WebMvcConfigurerAdapter {

//    @Bean
//    public ViewResolver viewResolver() {
//        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
//        resolver.setPrefix("/WEB-INF/pages/");
//        resolver.setSuffix(".jsp");
//        resolver.setExposeContextBeansAsAttributes(true);
//        return resolver;
//    }

    //Inject ContentNegotiationManager into ContentNegotiatingViewResolver
//    @Bean
//    public ViewResolver cnViewResolver(ContentNegotiationManager contentNegotiationManager){
//        ContentNegotiatingViewResolver contentNegotiatingViewResolver = new ContentNegotiatingViewResolver();
//        contentNegotiatingViewResolver.setContentNegotiationManager(contentNegotiationManager);
//        return contentNegotiatingViewResolver;
//    }
//
//    //Sets default response view to JSON by setting property in ContentNegotiationManager
//    @Override
//    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
//        configurer.ignoreAcceptHeader(true).defaultContentType(MediaType.APPLICATION_JSON);
//    }

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authenticatorInterceptor());
    }

    @Bean
    public TokenAuthenticatorInterceptor authenticatorInterceptor() {
        return new TokenAuthenticatorInterceptor();
    }
}
