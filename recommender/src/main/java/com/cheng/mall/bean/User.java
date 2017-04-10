package com.cheng.mall.bean;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

/**
 * 用户名模块实体类:
 * 
 * CREATE TABLE `user` ( `uid` int(11) NOT NULL AUTO_INCREMENT, `username`
 * varchar(255) DEFAULT NULL, `password` varchar(255) DEFAULT NULL, `name`
 * varchar(255) DEFAULT NULL, `email` varchar(255) DEFAULT NULL, `phone`
 * varchar(255) DEFAULT NULL, `addr` varchar(255) DEFAULT NULL, `state` int(11)
 * DEFAULT NULL, `code` varchar(64) DEFAULT NULL, PRIMARY KEY (`uid`) )
 * ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
 */
@Table(name = "user", uniqueConstraints = { @UniqueConstraint(columnNames = { "username" }) })
@Entity
public class User implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer uid;
	private Integer age;
	private String sex;

	@NotNull
	private String username;
	@NotNull
	private String password;
	private String name;
	private String email;
	private String phone;
	private String addr;
	private Integer state;
	private String code;
	// // 一个用户对应多个订单:
	// private Set<Order> orders = new HashSet<Order>();

	@GeneratedValue
	@Id
	public Integer getUid() {
		return uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

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

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Override
	public String toString() {
		return "User [uid=" + uid + ", age=" + age + ", sex=" + sex + ", username=" + username + ", password="
				+ password + ", name=" + name + ", email=" + email + ", phone=" + phone + ", addr=" + addr + ", state="
				+ state + ", code=" + code + "]";
	}

	// // 映射单向1-n的关联关系
	// // 使用@OneToMany来映射1-n的关联关系
	// // 使用@joinColumn来映射外键列的名称
	// @JoinColumn(name = "uid")
	// @OneToMany(fetch = FetchType.EAGER, cascade = { CascadeType.REMOVE })
	// public Set<Order> getOrders() {
	// return orders;
	// }
	//
	// public void setOrders(Set<Order> orders) {
	// this.orders = orders;
	// }

}
