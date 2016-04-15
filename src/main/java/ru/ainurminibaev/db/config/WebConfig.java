package ru.ainurminibaev.db.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.validation.DefaultMessageCodesResolver;
import org.springframework.validation.MessageCodesResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver;

/**
 * Created by ainurminibaev on 26.08.14.
 */
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = { "ru.ainurminibaev.db.controller" })
public class WebConfig extends WebMvcConfigurerAdapter {

	@Bean
	public FreeMarkerViewResolver viewResolver() {
		FreeMarkerViewResolver viewResolver = new FreeMarkerViewResolver();
		viewResolver.setCache(false);
		viewResolver.setSuffix(".ftl");
		viewResolver.setPrefix("");
		viewResolver.setContentType("text/html;charset=UTF-8");
		return viewResolver;
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		// registry.addResourceHandler("/resources/**").
		// addResourceLocations("/resources/")
		// .setCachePeriod(86400);
		registry.addResourceHandler("/css/**").addResourceLocations("/resources/css/").setCachePeriod(86400);
		registry.addResourceHandler("/js/**").addResourceLocations("/resources/js/").setCachePeriod(86400);
		registry.addResourceHandler("/fonts/**").addResourceLocations("/resources/fonts/").setCachePeriod(86400);
		registry.addResourceHandler("/images/**").addResourceLocations("/resources/images/").setCachePeriod(86400);

		registry.addResourceHandler("*.html").addResourceLocations("/WEB-INF/");
	}

	@Bean(name = "multipartResolver")
	public CommonsMultipartResolver commonsMultipartResolver() {
		CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver();
		commonsMultipartResolver.setMaxUploadSize(10000000);
		return commonsMultipartResolver;
	}

	// @Bean
	// MultipartConfigElement multipartConfigElement() {
	// MultipartConfigFactory factory = new MultipartConfigFactory();
	// factory.setMaxFileSize("5120MB");
	// factory.setMaxRequestSize("5120MB");
	// return factory.createMultipartConfig();
	// }

	@Bean
	public ResourceBundleMessageSource messageSource() {
		ResourceBundleMessageSource source = new ResourceBundleMessageSource();
		source.setBasename("messages/messages");
		source.setDefaultEncoding("UTF-8");
		source.setUseCodeAsDefaultMessage(true);
		return source;
	}

	@Override
	public MessageCodesResolver getMessageCodesResolver() {
		DefaultMessageCodesResolver messageCodesResolver = new DefaultMessageCodesResolver();
		messageCodesResolver.setMessageCodeFormatter(DefaultMessageCodesResolver.Format.POSTFIX_ERROR_CODE);
		return messageCodesResolver;
	}

}
