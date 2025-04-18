//package com.example.pickleball.config;
//
//import nz.net.ultraq.thymeleaf.LayoutDialect;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.thymeleaf.spring5.templateresolver.SpringTemplateResolver;
//import org.thymeleaf.spring5.view.ThymeleafViewResolver;
//import org.thymeleaf.spring5.SpringTemplateEngine;
//
//@Configuration
//public class ThymeleafConfig {
//
//    @Bean
//    public LayoutDialect layoutDialect() {
//        return new LayoutDialect();
//    }
//
//    @Bean
//    public SpringTemplateResolver templateResolver() {
//        SpringTemplateResolver resolver = new SpringTemplateResolver();
//        resolver.setPrefix("classpath:/templates/");
//        resolver.setSuffix(".html");
//        resolver.setTemplateMode("HTML");
//        return resolver;
//    }
//
//    @Bean
//    public ThymeleafViewResolver viewResolver() {
//        ThymeleafViewResolver resolver = new ThymeleafViewResolver();
//        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
//        templateEngine.setTemplateResolver(templateResolver());
//        templateEngine.addDialect(layoutDialect());
//        resolver.setTemplateEngine(templateEngine);
//        return resolver;
//    }
//}
