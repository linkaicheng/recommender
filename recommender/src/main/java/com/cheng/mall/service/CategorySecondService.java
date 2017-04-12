package com.cheng.mall.service;

import java.util.List;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.cheng.mall.bean.CategorySecond;
import com.cheng.mall.dao.CategorySecondRepository;

@Service
public class CategorySecondService {
	@Resource
	private CategorySecondRepository categorySecondRepository;

	/**
	 * 查询所有二级分类
	 * 
	 * @author linkaicheng
	 * @date 2017年4月8日 下午11:48:46
	 * @return
	 *
	 */
	public List<CategorySecond> findAllCategorySecond() {
		return categorySecondRepository.findAll();
	}

	/**
	 * 查
	 * 
	 * @author linkaicheng
	 * @date 2017年4月9日 下午3:18:24
	 * @param csid
	 * @return
	 *
	 */
	public CategorySecond findCategorySecondByCsid(Integer csid) {
		return categorySecondRepository.findOne(csid);
	}

	/**
	 * save,update ,delete 方法需要绑定事务.
	 * 
	 * @author linkaicheng
	 * @date 2017年4月8日 下午11:49:19
	 * @param category
	 * @return
	 *
	 */
	@Transactional
	public CategorySecond saveCategorySecond(CategorySecond categorySecond) {
		return categorySecondRepository.save(categorySecond);
	}

	@Transactional
	public CategorySecond updateCategorySecond(CategorySecond categorySecond) {
		if (categorySecond.getCsid() != null && categorySecond.getCategory() != null) {
			return categorySecondRepository.save(categorySecond);
		}
		return null;
	}

	@Transactional
	public CategorySecond deleteCategorySecond(Integer csid) {
		CategorySecond categorySesondDelete = categorySecondRepository.findOne(csid);
		if (categorySesondDelete != null) {
			categorySecondRepository.deleteCategorySecondByCsid(categorySesondDelete.getCsid());
		}
		return null;
	}
}
