package com.bioqwer.serverApp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.UrlBasedViewResolver;

/**
 * Defines callback methods to customize the Java-based configuration for Spring MVC enabled via @EnableWebMvc.
 */
@Configuration
@EnableWebMvc
@ComponentScan("com.bioqwer.serverApp")
public class WebResourceConfig extends WebMvcConfigurerAdapter {

    //Для видимости html в папке webapp!
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**").addResourceLocations("/*");
    }

    /**
     * {@link org.springframework.web.servlet.view.UrlBasedViewResolver}
     */
    @Bean
    public UrlBasedViewResolver setupViewResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();

        return resolver;
    }

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

}

