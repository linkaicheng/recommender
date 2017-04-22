package com.cheng.mall.service;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.cheng.mall.bean.Cart;
import com.cheng.mall.dao.CartRespository;

@Service
public class CartService {
	@Resource
	private CartRespository cartRespository;

	/**
	 * 
	 * @author linkaicheng
	 * @date 2017年4月18日 下午11:25:32
	 * @param uid
	 * @return
	 *
	 */
	public Cart findCartByUid(Integer uid) {
		return cartRespository.findCartByUid(uid);
	}

	/**
	 * 
	 * @author linkaicheng
	 * @date 2017年4月18日 下午11:26:17
	 * @param cart
	 * @return
	 *
	 */
	@Transactional
	public Cart createCart(Cart cart) {
		return cartRespository.save(cart);
	}

	@Transactional
	public Cart updateCart(Cart cart) {
		return cartRespository.save(cart);
	}

}
