package com.cheng.mall.dto;

import java.io.Serializable;

/**
 * 注册表单填写的信息
 * 
 * @author linkaicheng
 * @date 2017年4月16日 上午1:10:14
 *
 */
public class RegisterFormDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer age;
	private String sex;
	private String username;
	private String password;
	private String conPassword;
	private String name;
	private String email;

	private String phone;
	private String addr;

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConPassword() {
		return conPassword;
	}

	public void setConPassword(String conPassword) {
		this.conPassword = conPassword;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "RegisterFormDto [age=" + age + ", sex=" + sex + ", username=" + username + ", password=" + password
				+ ", conPassword=" + conPassword + ", name=" + name + ", email=" + email + ", phone=" + phone
				+ ", addr=" + addr + "]";
	}

}
