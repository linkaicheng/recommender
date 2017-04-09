package com.cheng.mall.dao.admin;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cheng.mall.bean.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {

}
