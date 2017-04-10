package com.cheng.mall.bean;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * 订单项的实体
 *
 */
@Table(name = "orderitem")
@Entity
public class OrderItem implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer itemid;
	private Integer count;
	private Double subtotal;
	// 商品外键:对象
	private Product product;
	// 订单外键:对象
	private Order order;

	@GeneratedValue
	@Id
	public Integer getItemid() {
		return itemid;
	}

	public void setItemid(Integer itemid) {
		this.itemid = itemid;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public Double getSubtotal() {
		return subtotal;
	}

	public void setSubtotal(Double subtotal) {
		this.subtotal = subtotal;
	}

	// 映射单向 n-1 的关联关系
	// 使用 @ManyToOne 来映射多对一的关联关系
	// 使用 @JoinColumn 来映射外键.
	// 可使用 @ManyToOne 的 fetch 属性来修改默认的关联属性的加载策略
	@JoinColumn(name = "pid")
	@ManyToOne(fetch = FetchType.EAGER)
	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	// 映射单向 n-1 的关联关系
	// 使用 @ManyToOne 来映射多对一的关联关系
	// 使用 @JoinColumn 来映射外键.
	// 可使用 @ManyToOne 的 fetch 属性来修改默认的关联属性的加载策略
	@JoinColumn(name = "oid")
	@ManyToOne(fetch = FetchType.EAGER)
	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

}
