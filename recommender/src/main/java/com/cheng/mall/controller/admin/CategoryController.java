package com.cheng.mall.controller.admin;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cheng.mall.bean.Category;
import com.cheng.mall.dto.PageDto;
import com.cheng.mall.service.CategoryService;

@RestController
@RequestMapping("/admin")
public class CategoryController {
	@Resource
	private CategoryService categoryService;

	/**
	 * 返回所有一级分类
	 * 
	 * @author linkaicheng
	 * @date 2017年5月11日 上午12:20:26
	 * @return
	 *
	 */
	@RequestMapping("/getCategoryListAll")
	public List<Category> getCategoryList() {
		return categoryService.findAllCategory();
	}

	@RequestMapping(value = { "/addCategory" }, method = RequestMethod.POST)
	public List<Category> addCategory(Category category) {
		categoryService.saveCategory(category);
		return categoryService.findAllCategory();
	}

	@RequestMapping(value = { "/deleteCategory" }, method = RequestMethod.POST)
	public List<Category> deleteCategory(Integer cid) {
		categoryService.deleteCategory(cid);
		return categoryService.findAllCategory();
	}

	@RequestMapping(value = { "/updateCategory" }, method = RequestMethod.POST)
	public List<Category> updateCategory(Category category) {
		categoryService.updateCategory(category);
		return categoryService.findAllCategory();
	}

	/**
	 * 分页查询
	 * 
	 * @author linkaicheng
	 * @date 2017年5月11日 上午12:45:41
	 * @param request
	 * @param pageNo
	 * @param pageSize
	 * @return
	 *
	 */
	@RequestMapping(value = { "/getCategoryListPage" }, method = RequestMethod.GET)
	public PageDto<Category> getCategoryListPage(HttpServletRequest request, Integer pageNo, Integer pageSize) {
		PageDto<Category> pageDto = new PageDto<>();
		List<Category> categories = categoryService.findCategorysPage(pageNo - 1, pageSize);
		Integer count = categoryService.findCount();
		pageDto.setList(categories);
		pageDto.setTotalCount(count);
		return pageDto;
	}

}
