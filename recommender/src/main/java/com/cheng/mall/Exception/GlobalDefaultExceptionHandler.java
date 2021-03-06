package com.cheng.mall.Exception;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/***
 * 全局异常处理 1、新建一个Class,这里取名为GlobalDefaultExceptionHandler*2、在class上添加注解，
 * 
 * @ControllerAdvice; 3、在class中添加一个方法 4、在方法上添加@ExcetionHandler拦截相应的异常信息；
 * 5、如果返回的是View -- 方法的返回值是ModelAndView;
 * 6、如果返回的是String或者是Json数据，那么需要在方法上添加@ResponseBody注解.
 *
 *
 */
@ControllerAdvice
public class GlobalDefaultExceptionHandler {
	@ExceptionHandler(Exception.class)
	@ResponseBody
	public String defaultExceptionHandler(HttpServletRequest req, Exception e) throws UnsupportedEncodingException {
		// 是返回的String.

		// ModelAndView -- 介绍 模板引擎...?
		// ModelAndView mv = new ModelAndView();
		// mv.setViewName(viewName);

		// return "Sorry server busy, please try again later";
		System.out.println("============================" + e.getMessage());
		return e.getMessage();
	}

}
