package com.bitacademy.config.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@PropertySource("classpath:com/bitacademy/msite/config/web/properties/fileupload/properties")
public class FileUploadConfig extends WebMvcConfigurerAdapter {

	@Autowired
	private Environment env;
	
	@Bean
	public MultipartResolver commonMultipartResolver() {
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
		multipartResolver.setMaxUploadSize(env.getProperty("fileupload.maxUploadSize",Long.class));
//		multipartResolver.setMaxUploadSizePerFile(100*1024*1024);
		multipartResolver.setMaxInMemorySize(env.getProperty("fileupload.maxInMemorySize",Integer.class));
		multipartResolver.setDefaultEncoding(env.getProperty("fileupload.defaultEncoding"));
		
		return multipartResolver;
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry
			.addResourceHandler(env.getProperty("fileupload.resouceMapping"))
			.addResourceLocations("file:" + env.getProperty("fileupload.resourceLocations"));
	}
	
	
}
