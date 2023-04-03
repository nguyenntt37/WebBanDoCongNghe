package com.duan;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.duan.interceptor.GloballInterceptor;
@Configuration
public class InterceptorConfig implements WebMvcConfigurer{
	@Autowired
	GloballInterceptor globallInterceptor;
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(globallInterceptor)
		.addPathPatterns("/**") /* Các đường dẫn hiển thị danh mục sản phẩm */
		.excludePathPatterns("/rest/**","/admin/**","/assets/**"); /* Loại trừ đường dẫn hiển thị danh mục sản phẩm */
	}

}
