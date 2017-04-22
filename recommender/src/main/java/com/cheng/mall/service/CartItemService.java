package com.cheng.mall.service;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.cheng.mall.bean.CartItem;
import com.cheng.mall.dao.CartItemRepository;
import com.cheng.mall.dao.CartRespository;

@Service
public class CartItemService {
	@Resource
	private CartRespository cartRespository;
	@Resource
	private CartItemRepository cartItemRepository;

	/**
	 * 增
	 * 
	 * @author linkaicheng
	 * @date 2017年4月21日 下午10:59:21
	 * @param cartItem
	 * @return
	 *
	 */
	@Transactional
	public CartItem createCartItem(CartItem cartItem) {
		CartItem cartItem2 = cartItemRepository.save(cartItem);
		return cartItem2;
	}

	/**
	 * 改
	 * 
	 * @author linkaicheng
	 * @date 2017年4月21日 下午10:59:33
	 * @param cartItem
	 * @return
	 *
	 */
	@Transactional
	public CartItem updateCartItem(CartItem cartItem) {
		CartItem cartItem2 = new CartItem();
		if (cartItem.getCartItemId() != null) {
			cartItem2 = cartItemRepository.save(cartItem);
		}
		return cartItem2;
	}

	/**
	 * 查
	 * 
	 * @author linkaicheng
	 * @date 2017年4月21日 下午11:00:47
	 * @param cartItemId
	 * @return
	 *
	 */
	public CartItem findCartItemById(Integer cartItemId) {
		return cartItemRepository.findOne(cartItemId);
	}

	/**
	 * 删
	 * 
	 * @author linkaicheng
	 * @date 2017年4月21日 下午11:01:42
	 * @param cartItemId
	 *
	 */
	@Transactional
	public void deleteCartItemById(Integer cartItemId) {
		cartItemRepository.delete(cartItemId);
	}

	@Transactional
	public void deleteCartItemByCartId(Integer cartId) {
		cartItemRepository.deleteCartItemByCartId(cartId);
	}

}
