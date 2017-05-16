package com.cheng.mall.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.impl.model.file.FileDataModel;
import org.apache.mahout.cf.taste.impl.similarity.EuclideanDistanceSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.similarity.ItemSimilarity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cheng.mall.bean.Category;
import com.cheng.mall.bean.Product;
import com.cheng.mall.bean.RecommenderItem;
import com.cheng.mall.bean.Record;
import com.cheng.mall.bean.User;
import com.cheng.mall.dto.CategoryNavigationDto;
import com.cheng.mall.dto.PageDto;
import com.cheng.mall.service.CategoryService;
import com.cheng.mall.service.ProductService;
import com.cheng.mall.service.recommender.RecommenderItemService;
import com.cheng.mall.service.recommender.RecordService;

/**
 * 
 * @author linkaicheng
 * @date 2017年4月16日 下午11:17:34
 *
 */
@Controller
public class ProductController {
	private Logger logger = Logger.getLogger(CartController.class);
	@Resource
	private CategoryService categoryService;
	@Resource
	private ProductService productService;
	@Resource
	private RecommenderItemService recommenderItemService;
	@Resource
	private RecordService recordService;

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
	 * 
	 * @author linkaicheng
	 * @date 2017年5月3日 下午4:58:27
	 * @return
	 *
	 */
	@ResponseBody
	@RequestMapping(value = { "/getRecommender" }, method = RequestMethod.GET)
	public List<RecommenderItem> getRecommender(HttpServletRequest request) {
		User user = (User) request.getSession().getAttribute("user");
		List<RecommenderItem> items = recommenderItemService.findRecommenderItemByUid(user.getUid());
		if (items != null && items.size() != 0) {
			return items;
		}
		return null;
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
	public String toProductList(Integer cid) {
		// request.getSession().setAttribute("cid", cid);
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
	public PageDto<Product> getProductsPageByCid(Integer cid, HttpServletRequest request, Integer pageNo,
			Integer pageSize) {
		PageDto<Product> pageDto = new PageDto<>();
		// Integer cid = (Integer) request.getSession().getAttribute("cid");
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
	public List<CategoryNavigationDto> getCategoryNavigations(HttpServletRequest request) {
		if (request.getSession().getAttribute("categoryNavigationDtos") != null) {
			return (List<CategoryNavigationDto>) request.getSession().getAttribute("categoryNavigationDtos");
		} else {
			List<Category> categories = categoryService.findAllCategory();
			List<CategoryNavigationDto> categoryNavigationDtos = new ArrayList<>();
			for (Category category : categories) {
				CategoryNavigationDto categoryNavigationDto = new CategoryNavigationDto();
				categoryNavigationDto.setCname(category.getCname());
				categoryNavigationDto.setCid(category.getCid());
				categoryNavigationDto.setCsSet(category.getCategorySeconds());
				categoryNavigationDtos.add(categoryNavigationDto);
			}
			request.getSession().setAttribute("categoryNavigationDtos", categoryNavigationDtos);
			return categoryNavigationDtos;
		}

	}

	// 商品详情页************************************
	/**
	 * 将商品id存到session，跳转到商品详情页
	 * 
	 * @author linkaicheng
	 * @date 2017年4月16日 下午11:46:45
	 * @param pid
	 * @param request
	 * @return
	 *
	 */
	@RequestMapping(value = { "/toProductDetail" }, method = RequestMethod.GET)
	public String toProduct(Integer pid, HttpServletRequest request) {
		request.getSession().setAttribute("pid", pid);
		return "/product";
	}

	/**
	 * 
	 * @author linkaicheng
	 * @date 2017年4月17日 上午12:55:10
	 * @param pid
	 * @return
	 *
	 */
	@ResponseBody
	@RequestMapping(value = { "/getProductByPid" }, method = RequestMethod.GET)
	public Product findProductByPid(Integer pid) {
		return productService.findProductByPid(pid);
	}

	/**
	 * 根据商品id返回相似商品推荐
	 * 
	 * @author linkaicheng
	 * @date 2017年5月4日 下午2:54:15
	 * @param pid
	 * @return
	 * @throws IOException
	 * @throws TasteException
	 *
	 */
	@ResponseBody
	@RequestMapping(value = { "/getRecommenderProductByPid" }, method = RequestMethod.GET)
	public List<Product> getRecommenderProductByPid(Integer pid) {
		List<Product> products = new ArrayList<>();
		// 根据csv文件生成相似度模型
		String file = "datafile/record.csv";
		DataModel dataModel;
		Record record = recordService.findRecordByPid(pid);
		if (record != null) {
			try {
				dataModel = new FileDataModel(new File(file));
				// 使用欧氏距离相似度
				final ItemSimilarity itemSimilarity = new EuclideanDistanceSimilarity(dataModel);
				for (Long id : itemSimilarity.allSimilarItemIDs(pid)) {
					Product product = productService.findProductByPid(id.intValue());
					logger.info("为商品:" + id + "推荐：" + "================" + id + "=======================");
					if (product.getPid() != pid) {
						products.add(product);
					}
				}
				// 如果该商品还没有相对应的推荐，返回最热门商品十件
				if (products.size() == 0) {
					products = productService.findHot();
				}
			} catch (Exception e) {
				e.printStackTrace();
				return productService.findHot();
			}
		} else {
			products = productService.findHot();
		}

		if (products.size() != 0) {
			return products;
		}

		return null;
	}

}
