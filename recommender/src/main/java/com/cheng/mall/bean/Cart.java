package com.cheng.mall.bean;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * 购物车对象
 * 
 * 
 */
@Table(name = "cart")
@Entity
public class Cart implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer cartId;
	private User user;
	private Set<CartItem> cartItems;
	private double total;

	@GeneratedValue
	@Id
	public Integer getCartId() {
		return cartId;
	}

	public void setCartId(Integer cartId) {
		this.cartId = cartId;
	}

	// 使用 @OneToOne 来映射 1-1 关联关系。
	// 若需要在当前数据表中添加主键则需要使用 @JoinColumn 来进行映射. 注意, 1-1 关联关系, 所以需要添加 unique=true
	@JoinColumn(name = "uid", unique = true)
	@OneToOne(fetch = FetchType.EAGER)
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	// 映射单向1-n的关联关系
	// 使用@OneToMany来映射1-n的关联关系
	// 使用@joinColumn来映射外键列的名称
	@JoinColumn(name = "cartId")
	@OneToMany(fetch = FetchType.EAGER, cascade = { CascadeType.REMOVE })
	public Set<CartItem> getCartItems() {
		return cartItems;
	}

	public void setCartItems(Set<CartItem> cartItems) {
		this.cartItems = cartItems;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "Cart [cartId=" + cartId + ", user=" + user + ", cartItems=" + cartItems + ", total=" + total + "]";
	}

	// // 购物车属性
	// // 购物项集合:Map的key就是商品pid,value:购物项
	// private Map<Integer, CartItem> map = new LinkedHashMap<Integer,
	// CartItem>();
	// // 购物总计:
	// private double total;
	//
	// // Cart对象中有一个叫cartItems属性.
	// public Collection<CartItem> getCartItems() {
	// return map.values();
	// }
	//
	// public double getTotal() {
	// return total;
	// }
	//
	// // 购物车的功能:
	// // 1.将购物项添加到购物车
	// public void addCart(CartItem cartItem) {
	// // 判断购物车中是否已经存在该购物项:
	// /*
	// * * 如果存在: * 数量增加 * 总计 = 总计 + 购物项小计 * 如果不存在: * 向map中添加购物项 * 总计 = 总计 +
	// * 购物项小计
	// */
	// // 获得商品id.
	// Integer pid = cartItem.getProduct().getPid();
	// // 判断购物车中是否已经存在该购物项:
	// if (map.containsKey(pid)) {
	// // 存在
	// CartItem _cartItem = map.get(pid);// 获得购物车中原来的购物项
	// _cartItem.setCount(_cartItem.getCount() + cartItem.getCount());
	// } else {
	// // 不存在
	// map.put(pid, cartItem);
	// }
	// // 设置总计的值
	// total += cartItem.getSubtotal();
	// }
	//
	// // 2.从购物车移除购物项
	// public void removeCart(Integer pid) {
	// // 将购物项移除购物车:
	// CartItem cartItem = map.remove(pid);
	// // 总计 = 总计 -移除的购物项小计:
	// total -= cartItem.getSubtotal();
	// }
	//
	// // 3.清空购物车
	// public void clearCart() {
	// // 将所有购物项清空
	// map.clear();
	// // 将总计设置为0
	// total = 0;
	// }
}
