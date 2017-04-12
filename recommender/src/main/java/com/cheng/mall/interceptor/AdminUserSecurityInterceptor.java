package com.cheng.mall.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.cheng.mall.bean.AdminUser;

/**
 * 后台管理拦截器，拦截非管理员用户
 * 
 * @author linkaicheng
 * @date 2017年4月11日 下午11:57:18
 *
 */
public class AdminUserSecurityInterceptor implements HandlerInterceptor {

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
		Object obj = request.getSession().getAttribute("adminUser");
		if (obj == null || !(obj instanceof AdminUser)) {
			response.sendRedirect(request.getContextPath() + "/toAdmin");
			return false;
		}

		return true;
	}

}
