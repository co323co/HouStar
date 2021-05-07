package com.ssafy.happyhouse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;


// web에 관련된 설정 java file
@Configuration 
// 환경설정파일임을 알려줘야한다.
public class WebConfiguration extends WebMvcConfigurerAdapter{
	// 인터셉터 설정
//	@Autowired
//	private ConfirmInterceptor confirmInterceptor;
//	
//	@Override
//	public void addInterceptors(InterceptorRegistry registry) {
//		registry.addInterceptor(confirmInterceptor).addPathPatterns("/article/**");
////		registry.addInterceptor(confirmInterceptor).addPathPatterns("/article/**"," 주소","주소 ");
		
	}


