package com.ssafy.happyhouse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;


// web에 관련된 설정 java file
@Configuration 
// 환경설정파일임을 알려줘야한다.
public class WebConfiguration implements WebMvcConfigurer{
	// 인터셉터 설정
//	@Autowired
//	private ConfirmInterceptor confirmInterceptor;
//	
//	@Override
//	public void addInterceptors(InterceptorRegistry registry) {
//		registry.addInterceptor(confirmInterceptor).addPathPatterns("/article/**");
//		registry.addInterceptor(confirmInterceptor).addPathPatterns("/article/**"," 주소","주소 ");
//	}
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		/// img/** 로 들어오는 요청은 /WEB-INF/images 에 매핑시키며 1년간 caching
//		registry.addResourceHandler("/**").addResourceLocations("/resources/");
		registry.addResourceHandler("/img/**").addResourceLocations("file:src/main/resources/static/img/");
		registry.addResourceHandler("/css/**").addResourceLocations("file:src/main/resources/css/");
		registry.addResourceHandler("/js/**").addResourceLocations("file:src/main/resources/js/");
	}
	
}


