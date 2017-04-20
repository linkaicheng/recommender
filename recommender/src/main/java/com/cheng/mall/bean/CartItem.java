package com.cheng.mall.bean;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * 购物项对象
 *
 */
@Table(name = "cartitem")
@Entity
@JsonIgnoreProperties(value = { "cart" })
public class CartItem {
	private Integer cartItemId;
	private Cart cart;
	private Product product; // 购物项中商品信息
	private int count; // 购买某种商品数量
	private double subtotal; // 购买某种商品小计

	@GeneratedValue
	@Id
	public Integer getCartItemId() {
		return cartItemId;
	}

	public void setCartItemId(Integer cartItemId) {
		this.cartItemId = cartItemId;
	}

	// 映射单向 n-1 的关联关系
	// 使用 @ManyToOne 来映射多对一的关联关系
	// 使用 @JoinColumn 来映射外键.
	// 可使用 @ManyToOne 的 fetch 属性来修改默认的关联属性的加载策略
	@JoinColumn(name = "cartId")
	@ManyToOne(fetch = FetchType.EAGER)
	public Cart getCart() {
		return cart;
	}

	public void setCart(Cart cart) {
		this.cart = cart;
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

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public double getSubtotal() {
		return subtotal;
	}

	public void setSubtotal(double subtotal) {
		this.subtotal = subtotal;
	}

	@Override
	public String toString() {
		return "CartItem [cartItemId=" + cartItemId + ", cart=" + cart + ", product=" + product + ", count=" + count
				+ ", subtotal=" + subtotal + "]";
	}

	// public Product getProduct() {
	// return product;
	// }
	//
	// public void setProduct(Product product) {
	// this.product = product;
	// }
	//
	// public int getCount() {
	// return count;
	// }
	//
	// public void setCount(int count) {
	// this.count = count;
	// }
	//
	// // 小计自动计算的.
	// public double getSubtotal() {
	// return count * product.getShop_price();
	// }
	// /*
	// * public void setSubtotal(double subtotal) { this.subtotal = subtotal; }
	// */

}
