package com.cheng.mall.controller;

import java.io.UnsupportedEncodingException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
import com.cheng.mall.util.Message;

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
	@ResponseBody
	@RequestMapping(value = { "/register" }, method = RequestMethod.POST)
	public Message register(@Validated({ RegisterFormDto.class }) RegisterFormDto registerFormDto) {
		User user = new User();
		Message message = validateDto(registerFormDto);
		if (message.getInfo() == null) {
			BeanUtils.copyProperties(registerFormDto, user);
			userService.save(user);
			message.setInfo("success");
		} else {
			message.setInfo(message.getInfo() + "请重新尝试");
		}
		return message;
	}

	/**
	 * 进入首页
	 * 
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@ResponseBody
	@RequestMapping(value = { "/login" }, method = RequestMethod.POST)
	public Message login(User user, HttpSession session) {
		User user2 = userService.login(user.getUsername(), user.getPassword());
		Message message = new Message();
		if (user2 != null) {
			session.setAttribute("user", user2);
			session.setMaxInactiveInterval(1800);// 30 分钟
			message.setInfo("success");
		} else {
			message.setInfo("用户名或密码不正确，请重新登录");
		}
		return message;
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

	public Message validateDto(RegisterFormDto dto) {
		Message message = new Message();

		if (dto == null) {
			return message;
		} else if (dto.getUsername() == null || "".equals(dto.getUsername().trim())) {
			message.setInfo("用户名不能为空为空.");
			return message;
		} else if (dto.getPassword() == null || dto.getPassword().trim().equals("")) {
			message.setInfo("密码不能为空.");
			return message;
		} else if (dto.getConPassword() == null || dto.getConPassword().trim().equals("")) {
			message.setInfo("需要确认密码.");
			return message;
		} else if (!(dto.getPassword().equals(dto.getConPassword()))) {
			message.setInfo("两次输入密码不一致.");
			return message;
		}
		Boolean flagEmail = false;
		Boolean flagPhone = false;
		try {
			// 验证邮箱
			String check = "^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(.[a-zA-Z0-9_-]+)+$";
			Pattern regex = Pattern.compile(check);
			if (dto.getEmail() != null) {
				Matcher matcher = regex.matcher(dto.getEmail());
				flagEmail = matcher.matches();
			}
			System.out.println("============" + flagEmail);
			// 验证手机
			/**
			 * 移动：134、135、136、137、138、139、150、151、157(TD)、158、159、187、188
			 * 
			 * 联通：130、131、132、152、155、156、185、186
			 * 
			 * 电信：133、153、180、189、（1349卫通）
			 */
			regex = Pattern.compile("^[1]([3][0-9]{1}|59|58|88|89)[0-9]{8}$");
			if (dto.getPhone() != null) {
				Matcher matcher = regex.matcher(dto.getPhone());
				flagPhone = matcher.matches();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		if (!flagEmail) {
			message.setInfo("邮箱格式不正确.");
			return message;
		} else if (!flagPhone) {
			message.setInfo("手机格式不正确.");
			return message;
		}

		return message;
	}

}
