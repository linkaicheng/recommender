package com.cheng.mall.controller;

import java.util.LinkedHashSet;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
/**
 * 订单相关操作
 * @author linkaicheng
 * @date 2017年4月22日 下午9:31:19
 *
 */
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cheng.mall.bean.Cart;
import com.cheng.mall.bean.CartItem;
import com.cheng.mall.service.CartItemService;

@Controller
public class OrderController {
	@Resource
	private CartItemService cartItemService;

	@ResponseBody
	@RequestMapping(value = { "/user/getItemsToOrder" }, method = RequestMethod.GET)
	public Cart findCartItems(HttpServletRequest request) {
		Set<CartItem> cartItems = new LinkedHashSet<>();
		Cart cart = new Cart();
		// 用从session中查处用户勾选的购物项id从数据库中查处购物项返回
		Integer[] cartItemIds = (Integer[]) request.getSession().getAttribute("cartItemIds");
		double total = 0.0;
		for (Integer cartItemId : cartItemIds) {
			CartItem cartItem = cartItemService.findCartItemById(cartItemId);
			if (cartItem != null) {
				total += cartItem.getSubtotal();
				cartItems.add(cartItem);
			}
		}
		cart.setCartItems(cartItems);
		cart.setTotal(total);
		return cart;
	}

}
