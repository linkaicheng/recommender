package com.cheng.mall.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cheng.mall.bean.Category;
import com.cheng.mall.bean.Product;
import com.cheng.mall.dto.CategoryNavigationDto;
import com.cheng.mall.dto.PageDto;
import com.cheng.mall.service.CategoryService;
import com.cheng.mall.service.ProductService;

/**
 * 
 * @author linkaicheng
 * @date 2017年4月16日 下午11:17:34
 *
 */
@Controller
public class ProductController {
	@Resource
	private CategoryService categoryService;
	@Resource
	private ProductService productService;

	/**
	 * 
	 * @author linkaicheng
	 * @date 2017年4月12日 下午10:27:57
	 * @return
	 *
	 */
	@ResponseBody
	@RequestMapping(value = { "/getCategoryList" }, method = RequestMethod.GET)
	public List<Category> getCategoryList() {
		return categoryService.findAllCategory();
	}

	/**
	 * 返回十件热门商品
	 * 
	 * @author linkaicheng
	 * @date 2017年4月12日 下午11:49:52
	 * @return
	 *
	 */
	@ResponseBody
	@RequestMapping(value = { "/getProductHot" }, method = RequestMethod.GET)
	public List<Product> getProductHot() {
		return productService.findHot();
	}

	/**
	 * 返回十件最新商品
	 * 
	 * @author linkaicheng
	 * @date 2017年4月12日 下午11:50:47
	 * @return
	 *
	 */
	@ResponseBody
	@RequestMapping(value = { "/getProductNew" }, method = RequestMethod.GET)
	public List<Product> getProductNew() {
		return productService.findNew();
	}

	/**
	 * 返回商品列表页
	 * 
	 * @author linkaicheng
	 * @date 2017年4月14日 上午12:32:30
	 * @param cid
	 * @return
	 *
	 */
	@RequestMapping(value = { "/toProductList" }, method = RequestMethod.GET)
	public String toProductList(Integer cid, HttpServletRequest request) {
		request.getSession().setAttribute("cid", cid);
		return "/productList";
	}

	/**
	 * 通过一级分类id查询商品
	 * 
	 * @author linkaicheng
	 * @date 2017年4月16日 上午1:15:31
	 * @param request
	 * @return
	 *
	 */
	@ResponseBody
	@RequestMapping(value = { "/getProductsPageByCid" }, method = RequestMethod.GET)
	public PageDto<Product> getProductsPageByCid(HttpServletRequest request, Integer pageNo, Integer pageSize) {
		PageDto<Product> pageDto = new PageDto<>();
		Integer cid = (Integer) request.getSession().getAttribute("cid");
		List<Product> products = productService.findProductsPageByCid(cid, pageNo - 1, pageSize);
		Integer count = productService.findCountCid(cid);
		pageDto.setList(products);
		pageDto.setTotalCount(count);
		return pageDto;
	}

	/**
	 * 通过二级分类id查询商品 分页
	 * 
	 * @author linkaicheng
	 * @date 2017年4月16日 下午7:06:38
	 * @param csid
	 * @param pageNo
	 * @param pageSize
	 * @return
	 *
	 */
	@ResponseBody
	@RequestMapping(value = { "/getProductsPageByCsid" }, method = RequestMethod.GET)
	public PageDto<Product> getProductsPageByCsid(Integer csid, Integer pageNo, Integer pageSize) {
		PageDto<Product> pageDto = new PageDto<>();
		List<Product> products = productService.findProductsPageByCsid(csid, pageNo - 1, pageSize);
		Integer count = productService.findCountCsid(csid);
		pageDto.setList(products);
		pageDto.setTotalCount(count);
		return pageDto;
	}

	/**
	 * 返回商品列表页左侧的分类导航
	 * 
	 * @author linkaicheng
	 * @date 2017年4月16日 下午4:04:25
	 * @return
	 *
	 */
	@ResponseBody
	@RequestMapping(value = { "/getCategoryNavigations" }, method = RequestMethod.GET)
	public List<CategoryNavigationDto> getCategoryNavigations() {
		List<Category> categories = categoryService.findAllCategory();
		List<CategoryNavigationDto> categoryNavigationDtos = new ArrayList<>();
		for (Category category : categories) {
			CategoryNavigationDto categoryNavigationDto = new CategoryNavigationDto();
			categoryNavigationDto.setCname(category.getCname());
			categoryNavigationDto.setCid(category.getCid());
			categoryNavigationDto.setCsSet(category.getCategorySeconds());
			categoryNavigationDtos.add(categoryNavigationDto);
		}

		return categoryNavigationDtos;
	}

}
