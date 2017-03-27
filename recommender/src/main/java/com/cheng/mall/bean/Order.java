package com.cheng.mall.bean;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * @author linkc 订单实体 CREATE TABLE `orders` ( `oid` int(11) NOT NULL
 *         AUTO_INCREMENT, `total` double DEFAULT NULL, `ordertime` datetime
 *         DEFAULT NULL, `state` int(11) DEFAULT NULL, `name` varchar(20)
 *         DEFAULT NULL, `phone` varchar(20) DEFAULT NULL, `addr` varchar(50)
 *         DEFAULT NULL, `uid` int(11) DEFAULT NULL, PRIMARY KEY (`oid`), KEY
 *         `FKC3DF62E5AA3D9C7` (`uid`), CONSTRAINT `FKC3DF62E5AA3D9C7` FOREIGN
 *         KEY (`uid`) REFERENCES `user` (`uid`) ) ENGINE=InnoDB
 *         AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
 */
@Table(name = "orders")
@Entity
public class Order {
	private Integer oid;
	private Double total;
	private Date ordertime;
	private Integer state;// 1:未付款 2:订单已经付款 3:已经发货 4:订单结束
	private String name;
	private String phone;
	private String addr;
	// 用户的外键:对象
	private User user;
	// 配置订单项的集合
	private Set<OrderItem> orderItems = new HashSet<OrderItem>();

	@GeneratedValue
	@Id
	public Integer getOid() {
		return oid;
	}

	public void setOid(Integer oid) {
		this.oid = oid;
	}

	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

	@Temporal(TemporalType.TIMESTAMP)
	public Date getOrdertime() {
		return ordertime;
	}

	public void setOrdertime(Date ordertime) {
		this.ordertime = ordertime;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	// 映射单向 n-1 的关联关系
	// 使用 @ManyToOne 来映射多对一的关联关系
	// 使用 @JoinColumn 来映射外键.
	// 可使用 @ManyToOne 的 fetch 属性来修改默认的关联属性的加载策略
	@JoinColumn(name = "uid")
	@ManyToOne(fetch = FetchType.LAZY)
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	// 映射单向1-n的关联关系
	// 使用@OneToMany来映射1-n的关联关系
	// 使用@joinColumn来映射外键列的名称
	@JoinColumn(name = "oid")
	@OneToMany(fetch = FetchType.EAGER, cascade = { CascadeType.REMOVE })
	public Set<OrderItem> getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(Set<OrderItem> orderItems) {
		this.orderItems = orderItems;
	}

}
