package com.cheng.mall;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.cheng.mall.interceptor.AdminUserSecurityInterceptor;
import com.cheng.mall.interceptor.UserSecurityInterceptor;

/**
 * 
 * @author linkaicheng
 *
 */
@Configuration
@EnableWebMvc
public class WebConfig extends WebMvcConfigurerAdapter {
	@Bean
	public ViewResolver viewResolver() {
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix("/WEB-INF/templates/");
		resolver.setSuffix(".html");
		resolver.setExposeContextBeansAsAttributes(true);
		return resolver;
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		// registry.addResourceHandler("/*.html").addResourceLocations("classpath:/templates/");
		// registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
		registry.addResourceHandler("/static/**").addResourceLocations("WEB-INF/static/");
	}

	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}

	/**
	 * 配置拦截器
	 * 
	 * @author lance
	 * @param registry
	 */
	public void addInterceptors(InterceptorRegistry registry) {
		// 普通用户是否登录
		registry.addInterceptor(new UserSecurityInterceptor()).addPathPatterns("/user/**");
		// 管理员用户是否登录
		registry.addInterceptor(new AdminUserSecurityInterceptor()).addPathPatterns("/admin/**");
	}
}