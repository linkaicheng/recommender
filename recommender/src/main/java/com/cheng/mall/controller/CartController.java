package com.cheng.mall.controller;

import java.util.LinkedHashSet;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
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
	private Logger logger = Logger.getLogger(CartController.class);

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

	/**
	 * 转到购物车页面
	 * 
	 * @author linkaicheng
	 * @date 2017年4月21日 下午10:22:36
	 * @param request
	 * @return
	 *
	 */
	@RequestMapping(value = { "/user/toCart" }, method = RequestMethod.GET)
	public String toCart(HttpServletRequest request) {
		return "/cart";
	}

	/**
	 * 删除购物项
	 * 
	 * @author linkaicheng
	 * @date 2017年4月22日 上午11:03:05
	 * @param request
	 * @param cartItemId
	 * @return
	 *
	 */
	@ResponseBody
	@RequestMapping(value = { "/user/deleteCartItem" }, method = RequestMethod.GET)
	public Cart deleteCartItem(HttpServletRequest request, Integer cartItemId) {
		User user = (User) request.getSession().getAttribute("user");
		Cart cart = cartService.findCartByUid(user.getUid());
		CartItem cartItem = cartItemService.findCartItemById(cartItemId);
		cart.setTotal(cart.getTotal() - cartItem.getSubtotal());
		cartService.updateCart(cart);
		// 将购物车中的购物项移除
		cart.getCartItems().remove(cartItem);
		cartItemService.deleteCartItemById(cartItemId);
		return cart;
	}

	/**
	 * 修改购物车中商品数量
	 * 
	 * @author linkaicheng
	 * @date 2017年4月22日 上午1:12:50
	 * @param request
	 * @param cartItemId
	 * @param count
	 * @return
	 *
	 */
	@ResponseBody
	@RequestMapping(value = { "/user/changeCount" }, method = RequestMethod.GET)
	public Cart changeCartItemCount(HttpServletRequest request, Integer cartItemId, Integer count) {
		CartItem cartItem = cartItemService.findCartItemById(cartItemId);
		cartItem.setCount(count);
		cartItem.setSubtotal(cartItem.getProduct().getShop_price() * count);
		cartItemService.updateCartItem(cartItem);
		User user = (User) request.getSession().getAttribute("user");
		Cart cart = cartService.findCartByUid(user.getUid());
		double total = 0.0;
		for (CartItem cartItem2 : cart.getCartItems()) {
			total = total + cartItem2.getSubtotal();
		}
		cart.setTotal(total);
		cartService.updateCart(cart);

		return cart;
	}

	/**
	 * 清空购物车
	 * 
	 * @author linkaicheng
	 * @date 2017年4月22日 下午3:00:00
	 * @param request
	 * @return
	 *
	 */
	@ResponseBody
	@RequestMapping(value = { "/user/deleteCart" }, method = RequestMethod.GET)
	public Cart deleteCart(HttpServletRequest request) {
		User user = (User) request.getSession().getAttribute("user");
		Cart cart = cartService.findCartByUid(user.getUid());
		cart.setTotal(0.0);
		cartService.updateCart(cart);
		// 清空数据库中的购物车
		cartItemService.deleteCartItemByCartId(cart.getCartId());
		cart.setCartItems(null);
		return cart;
	}

}
