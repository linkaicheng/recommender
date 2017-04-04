package com.cheng.mall.controller.demo;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cheng.mall.bean.AdminUser;
import com.cheng.mall.bean.demo.Demo;
import com.cheng.mall.service.CatService;

/**
 * 在这里我们使用RestController （等待于 @Controller 和 @RequestBody）
 * 
 * @author linkc
 *
 */
@RestController
public class HelloController {
	@Resource
	CatService catService;

	/**
	 * 在这里我们使用@RequestMapping 建立请求映射: http://127.0.0.1:8080/hello
	 * 
	 * @return
	 */
	@RequestMapping("/hello")
	public String hello() {
		return "hello-2016-12-11.v.02";
	}

	@RequestMapping("/hello2")
	public String hello2() {
		return "hello2-2016";
	}

	@RequestMapping("/hello3")
	public int hello3() {
		return 1 / 0;
	}

	/**
	 * Spring Boot默认使用的json解析框架是jackson
	 * 
	 * @return
	 */
	@RequestMapping("/getDemo")
	public Demo getDemo() {
		Demo demo = new Demo();
		demo.setId(1);
		demo.setName("张三");
		demo.setCreateTime(new Date());
		demo.setRemarks("这是备注信息");
		return demo;
	}

	/**
	 * Spring Boot默认使用的json解析框架是jackson
	 * 
	 * @return
	 */
	@RequestMapping("/getAdmin")
	public List<AdminUser> getAdmin() {

		return catService.getAllAdmin();
	}

}
