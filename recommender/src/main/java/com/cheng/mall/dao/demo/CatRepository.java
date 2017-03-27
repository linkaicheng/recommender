package com.cheng.mall.dao.demo;

import org.springframework.data.repository.CrudRepository;

import com.cheng.mall.bean.demo.Cat;

public interface CatRepository extends CrudRepository<Cat, Integer> {

}
