package com.cheng.mall;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.HttpMessageConverters;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;

/**
 * 在这里我们使用@SpringBootApplication指定这是一个 spring boot的应用程序.
 * 
 * @author linkc
 *
 */
// 启注解事务管理，等同于xml配置方式的 <tx:annotation-driven />
@EnableTransactionManagement
@SpringBootApplication
// 使用ComponentScan覆盖SpringBootApplication的默认扫描路径
@ComponentScan({ "com.cheng.mall" })
public class App {

	/**
	 * 这是springloader的配置方式：-javaagent:.\lib\springloaded-1.2.4.RELEASE.jar
	 * -noverify
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		/*
		 * 在main方法进行启动我们的应用程序.
		 */
		SpringApplication.run(App.class, args);
	}

	/**
	 * 使用第三方 fastjson进行json解析 在这里我们使用 @Bean注入 fastJsonHttpMessageConvert
	 * 
	 * @return
	 */
	@Bean
	public HttpMessageConverters fastJsonHttpMessageConverters() {
		// 1、需要先定义一个 convert 转换消息的对象;
		FastJsonHttpMessageConverter fastConverter = new FastJsonHttpMessageConverter();

		// 2、添加fastJson 的配置信息，比如：是否要格式化返回的json数据;
		FastJsonConfig fastJsonConfig = new FastJsonConfig();
		fastJsonConfig.setSerializerFeatures(SerializerFeature.PrettyFormat);

		// 3、在convert中添加配置信息.
		fastConverter.setFastJsonConfig(fastJsonConfig);

		HttpMessageConverter<?> converter = fastConverter;
		return new HttpMessageConverters(converter);
	}

}
