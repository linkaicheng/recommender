package com.cheng.mall.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cheng.mall.bean.User;
import com.cheng.mall.dto.RegisterFormDto;
import com.cheng.mall.service.UserService;
import com.cheng.mall.service.admin.AdminUserService;

/**
 * 用户登录注册
 * 
 * @author linkaicheng
 *
 */
@Controller
public class UserController {
	@Resource
	private UserService userService;
	@Resource
	private AdminUserService adminUserService;

	/**
	 * 进入首页
	 * 
	 * @return
	 */
	@RequestMapping(value = { "/register" }, method = RequestMethod.POST)
	public String register(@Validated({ RegisterFormDto.class }) RegisterFormDto registerFormDto) {
		User user = new User();
		if (validateDto(registerFormDto)) {
			BeanUtils.copyProperties(registerFormDto, user);
			userService.save(user);
			return "login";
		}
		return "error";
	}

	/**
	 * 进入首页
	 * 
	 * @return
	 */
	@RequestMapping(value = { "/login" }, method = RequestMethod.POST)
	public String login(String userName, String password, HttpSession session) {
		User user = userService.login(userName, password);
		if (user != null) {
			session.setAttribute("user", user);
			session.setMaxInactiveInterval(1800);// 30 分钟
			return "index";
		}
		return "error";
	}

	/**
	 * 返回session中保存的用户
	 * 
	 * @author linkaicheng
	 * @date 2017年4月10日 下午10:56:52
	 * @param request
	 * @return
	 *
	 */
	@ResponseBody
	@RequestMapping(value = { "/user/getUserFromSession" }, method = RequestMethod.GET)
	public User getUserFromSession(HttpServletRequest request) {
		User user = (User) request.getSession().getAttribute("user");
		return user;
	}

	public boolean validateDto(RegisterFormDto dto) {
		if (dto == null) {
			return false;
		}
		if (dto.getUsername() == null || "".equals(dto.getUsername().trim())) {
			return false;
		} else if (dto.getPassword() == null || dto.getPassword().trim().equals("")) {
			return false;
		} else if (dto.getConPassword() == null || dto.getConPassword().trim().equals("")) {
			return false;

		} else if (!(dto.getPassword().equals(dto.getConPassword()))) {
			return false;
		}
		return true;
	}

}
