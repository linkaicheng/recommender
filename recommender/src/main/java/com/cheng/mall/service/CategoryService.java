package com.cheng.mall.service;

import java.util.List;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.cheng.mall.bean.Category;
import com.cheng.mall.dao.CategoryRepository;

@Service
public class CategoryService {
	@Resource
	private CategoryRepository categoryRepository;

	/**
	 * save,update ,delete 方法需要绑定事务.
	 * 
	 * @author linkaicheng
	 * @date 2017年4月8日 下午3:56:25
	 * @param
	 *
	 */
	@Transactional
	public Category saveCategory(Category category) {
		return categoryRepository.save(category);
	}

	/**
	 * save,update ,delete 方法需要绑定事务.
	 * 
	 * @author linkaicheng
	 * @date 2017年4月8日 下午3:56:25
	 * @param
	 *
	 */
	@Transactional
	public void deleteCategory(Integer cid) {
		Category categoryDelete = categoryRepository.findCategoryByCid(cid);
		if (categoryDelete != null) {
			categoryRepository.deleteCategoryByCid(categoryDelete.getCid());
		}
	}

	@Transactional
	public Category updateCategory(Category category) {
		if (category.getCid() != null) {
			return categoryRepository.save(category);
		}
		return null;
	}

	public List<Category> findAllCategory() {
		return categoryRepository.findAll();
	}

	public Category findCategoryByCname(String cname) {
		return categoryRepository.findCategoryByCname(cname);
	}

	public Category findCategoryByCid(Integer cid) {
		// return categoryRepository.findCategoryByCid(cid);
		return categoryRepository.findOne(cid);
	}

}
