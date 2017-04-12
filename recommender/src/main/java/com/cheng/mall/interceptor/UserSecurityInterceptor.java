package com.cheng.mall.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.cheng.mall.bean.User;

/**
 * 拦截未登录的用户信息
 * 
 * @author linkaicheng
 * @date 2017年4月11日 下午11:27:30
 *
 */
public class UserSecurityInterceptor implements HandlerInterceptor {

	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object arg2) throws Exception {
		// 验证用户是否登陆
		Object obj = request.getSession().getAttribute("user");
		if ((obj == null || !(obj instanceof User))) {
			response.sendRedirect(request.getContextPath() + "/toLogin");
			return false;
		}

		return true;
	}

}
