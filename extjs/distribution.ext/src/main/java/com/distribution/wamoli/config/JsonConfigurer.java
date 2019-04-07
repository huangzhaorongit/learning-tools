package com.distribution.wamoli.config;

import com.distribution.wamoli.common.interceptor.DateConverter;
import com.distribution.wamoli.common.interceptor.method.annotation.BeanArgumentResolver;
import com.distribution.wamoli.common.interceptor.method.annotation.ListArgumentResolver;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.embedded.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.converter.support.AllEncompassingFormHttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Configuration of web application with Servlet 3.0 APIs.
 */
@Configuration
//@EnableWebMvc
@ComponentScan(excludeFilters = @ComponentScan.Filter(type= FilterType.ANNOTATION, value={Service.class}),
    basePackages = {
        "com.distribution.wamoli.**.controller", "com.distribution.wamoli.**.web", "com.distribution.wamoli.**.rest", "org.jeecgframework.poi.excel.view"
    })
public class JsonConfigurer extends WebMvcConfigurerAdapter implements EmbeddedServletContainerCustomizer {

    private final Logger log = LoggerFactory.getLogger(JsonConfigurer.class);

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/app/**").addResourceLocations("/app/");
        registry.addResourceHandler("/build/**").addResourceLocations("/build/");
        registry.addResourceHandler("/excelTemplate/**").addResourceLocations("/excelTemplate/");
        registry.addResourceHandler("/expand/**").addResourceLocations("/expand/");
        registry.addResourceHandler("/ext/**").addResourceLocations("/ext/");
        registry.addResourceHandler("/logs/**").addResourceLocations("/logs/");
        registry.addResourceHandler("/overrides/**").addResourceLocations("/overrides/");
        registry.addResourceHandler("/packages/**").addResourceLocations("/packages/");
        registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
        registry.addResourceHandler("/sass/**").addResourceLocations("/sass/");
        registry.addResourceHandler("/static/**").addResourceLocations("/static/");
    }

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    @Bean
    public CommonsMultipartResolver multipartResolver() {
        CommonsMultipartResolver resolver = new CommonsMultipartResolver();
        resolver.setDefaultEncoding("UTF-8");
        resolver.setMaxUploadSize(5242880);
        return resolver;
    }

    @Bean
    public CookieLocaleResolver localeResolver() {
        return new CookieLocaleResolver();
    }

    @Bean
    public RequestMappingHandlerAdapter requestMappingHandlerAdapter() {
        RequestMappingHandlerAdapter handlerAdapter = new RequestMappingHandlerAdapter();
        handlerAdapter.setWebBindingInitializer(new DateConverter());
        List<HttpMessageConverter<?>> list = new ArrayList<>();
        list.add(mappingJackson2HttpMessageConverter());
//        list.add(new XmlAwareFormHttpMessageConverter());
        list.add(new AllEncompassingFormHttpMessageConverter());
        handlerAdapter.setMessageConverters(list);
        List<HandlerMethodArgumentResolver> res = new ArrayList<>();
        res.add(new BeanArgumentResolver());
        res.add(new ListArgumentResolver());
        handlerAdapter.setCustomArgumentResolvers(res);
        return handlerAdapter;
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("forward:index.html");
        registry.addViewController("/login").setViewName("forward:index.html");
    }

    @Bean
    public MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter() {
        MappingJackson2HttpMessageConverter messageConverter = new MappingJackson2HttpMessageConverter();
        messageConverter.setObjectMapper(objectMapper());
        List<MediaType> list = new ArrayList();
        list.add(MediaType.TEXT_PLAIN);
        list.add(MediaType.APPLICATION_JSON);
        messageConverter.setSupportedMediaTypes(list);
        return messageConverter;
    }

    @Bean
    public ObjectMapper objectMapper() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        return mapper;
    }

    /**
     * Customize the Servlet engine: Mime types, the document root, the cache.
     */
    @Override
    public void customize(ConfigurableEmbeddedServletContainer container) {
        MimeMappings mappings = new MimeMappings(MimeMappings.DEFAULT);
        // IE issue, see https://github.com/jhipster/generator-jhipster/pull/711
        mappings.add("html", "text/html;charset=utf-8");
        // CloudFoundry issue, see https://github.com/cloudfoundry/gorouter/issues/64
        mappings.add("json", "text/html;charset=utf-8");
        container.setMimeMappings(mappings);
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**");
    }
}
