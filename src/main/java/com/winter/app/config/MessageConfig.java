// MessageConfig.java
package com.winter.app.config;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired; // 추가
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.validation.Validator; // 추가
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean; // 추가
import org.springframework.context.MessageSource; // 추가

@Configuration
public class MessageConfig implements WebMvcConfigurer{
    
    @Autowired
    private MessageSource messageSource;

    @Bean
    LocaleResolver localeResolver() {
        CookieLocaleResolver localeResolver = new CookieLocaleResolver();
        localeResolver.setDefaultLocale(Locale.KOREAN);
        return localeResolver;
    }
    
    @Override
    public Validator getValidator() {
        LocalValidatorFactoryBean validator = new LocalValidatorFactoryBean();
        validator.setValidationMessageSource(messageSource);
        
        return validator;
    }
}