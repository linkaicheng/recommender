package com.cheng.mall.controller;

import java.util.LinkedHashSet;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cheng.mall.bean.Cart;
import com.cheng.mall.bean.CartItem;
import com.cheng.mall.bean.Product;
import com.cheng.mall.bean.User;
import com.cheng.mall.service.CartItemService;
import com.cheng.mall.service.CartService;
import com.cheng.mall.service.ProductService;

/**
 * 购物车相关操作
 * 
 * @author linkaicheng
 * @date 2017年4月17日 下午11:52:37
 *
 */
@Controller
public class CartController {
	@Resource
	private CartService cartService;
	@Resource
	private CartItemService cartItemService;
	@Resource
	private ProductService productService;

	/**
	 * 将商品加入到购物车
	 * 
	 * @author linkaicheng
	 * @date 2017年4月19日 下午11:37:41
	 * @param pid
	 * @param count
	 * @param request
	 * @return
	 *
	 */
	@RequestMapping(value = { "/user/saveCart" }, method = RequestMethod.POST)
	public String addCart(Integer pid, Integer count, HttpServletRequest request) {
		// 根据pid进行查询商品:
		Product product = productService.findProductByPid(pid);
		User user = (User) request.getSession().getAttribute("user");
		if (user != null && user.getUid() != null) {
			Cart cart = cartService.findCartByUid(user.getUid());
			if (cart == null) {
				cart = new Cart();
				cart.setUser(user);
				cart = cartService.createCart(cart);
			}
			Boolean isItemExist = false;
			Set<CartItem> cartItems = new LinkedHashSet<>();
			for (CartItem cartItem : cart.getCartItems()) {
				if (cartItem.getProduct().getPid().equals(pid)) {
					cartItem.setCount(cartItem.getCount() + count);
					cartItem.setSubtotal(cartItem.getProduct().getShop_price() * cartItem.getCount());
					isItemExist = true;
				}
				cartItems.add(cartItem);
			}
			cart.setCartItems(cartItems);
			if (!isItemExist) {
				CartItem cartItem = new CartItem();
				cartItem.setCart(cart);
				cartItem.setCount(count);
				cartItem.setProduct(product);
				cartItem.setSubtotal(count * product.getShop_price());
				cartItemService.createCartItem(cartItem);
				Set<CartItem> cartItems2 = cart.getCartItems();
				cartItems2.add(cartItem);
				cart.setCartItems(cartItems2);
			}

			double total = 0.0;
			for (CartItem cartItem2 : cart.getCartItems()) {
				total = total + cartItem2.getSubtotal();
			}
			cart.setTotal(total);
			cartService.updateCart(cart);
		}
		return "/cart";
	}

	/**
	 * 返回已登录用户的购物车
	 * 
	 * @author linkaicheng
	 * @date 2017年4月19日 下午11:58:35
	 * @param request
	 * @return
	 *
	 */
	@ResponseBody
	@RequestMapping(value = { "/user/getCart" }, method = RequestMethod.GET)
	public Cart getCart(HttpServletRequest request) {
		User user = (User) request.getSession().getAttribute("user");
		return cartService.findCartByUid(user.getUid());
	}

}
