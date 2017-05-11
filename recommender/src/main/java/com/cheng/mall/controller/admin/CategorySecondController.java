package com.cheng.mall.controller.admin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cheng.mall.bean.Category;
import com.cheng.mall.bean.CategorySecond;
import com.cheng.mall.dto.AdminCategorySecondDto;
import com.cheng.mall.dto.PageDto;
import com.cheng.mall.service.CategorySecondService;
import com.cheng.mall.service.CategoryService;
import com.cheng.mall.util.Message;

@RestController
@RequestMapping("/admin")
public class CategorySecondController {
	@Resource
	private CategorySecondService categorySecondService;
	@Resource
	private CategoryService categoryService;

	/**
	 * 返回二级分类列表
	 * 
	 * @author linkaicheng
	 * @date 2017年4月8日 下午11:50:27
	 * @return
	 *
	 */
	@RequestMapping(value = { "/getCsListAll" }, method = RequestMethod.GET)
	public List<AdminCategorySecondDto> getCategorySecondList() {
		List<CategorySecond> categorySeconds = categorySecondService.findAllCategorySecond();
		List<AdminCategorySecondDto> adminCategorySecondDtos = new ArrayList<>();
		// 将页面需要的信息封装到dto
		for (CategorySecond categorySecond : categorySeconds) {
			AdminCategorySecondDto adminCategorySecondDto = new AdminCategorySecondDto();
			adminCategorySecondDto.setCid(categorySecond.getCategory().getCid());
			adminCategorySecondDto.setCname(categorySecond.getCategory().getCname());
			adminCategorySecondDto.setCsid(categorySecond.getCsid());
			adminCategorySecondDto.setCsname(categorySecond.getCsname());
			adminCategorySecondDtos.add(adminCategorySecondDto);
		}
		return adminCategorySecondDtos;
	}

	/**
	 * 添加一个二级分类
	 * 
	 * @author linkaicheng
	 * @date 2017年4月8日 下午11:51:05
	 * @return
	 *
	 */
	@RequestMapping(value = { "/addCategorySecond" }, method = RequestMethod.POST)
	public Message addCategorySecond(String csname, Integer cid) {

		CategorySecond categorySecond = new CategorySecond();
		categorySecond.setCsname(csname);
		Category category = categoryService.findCategoryByCid(cid);
		if (category != null) {
			categorySecond.setCategory(category);
			categorySecondService.saveCategorySecond(categorySecond);
		}
		Message message = new Message();
		message.setInfo("success");
		return message;
	}

	/**
	 * 改
	 * 
	 * @author linkaicheng
	 * @date 2017年4月9日 上午10:47:36
	 * @param categorySecond
	 * @return
	 *
	 */
	@RequestMapping(value = { "/updateCategorySecond" }, method = RequestMethod.POST)
	public Message updateCategorySecond(Integer csid, String csname, Integer cid) {
		CategorySecond categorySecond = categorySecondService.findCategorySecondByCsid(csid);
		Category category = categoryService.findCategoryByCid(cid);
		if (categorySecond != null && category != null) {
			categorySecond.setCsname(csname);
			categorySecond.setCategory(category);
			categorySecondService.saveCategorySecond(categorySecond);
		}
		Message message = new Message();
		message.setInfo("success");
		return message;
	}

	/**
	 * 删
	 * 
	 * @author linkaicheng
	 * @date 2017年4月9日 上午10:49:52
	 * @param categorySecond
	 * @return
	 *
	 */
	@RequestMapping(value = { "/deleteCategorySecond" }, method = RequestMethod.POST)
	public Message deleteCategorySecond(Integer csid) {
		// System.out.println(csid);
		if (csid != null) {
			categorySecondService.deleteCategorySecond(csid);
		}
		Message message = new Message();
		message.setInfo("success");
		return message;
	}

	/**
	 * 分页查询二级分类
	 * 
	 * @author linkaicheng
	 * @date 2017年5月11日 上午1:10:39
	 * @param request
	 * @param pageNo
	 * @param pageSize
	 * @return
	 *
	 */
	@RequestMapping(value = { "/getCsListPage" }, method = RequestMethod.GET)
	public Map<String, Object> getCategoryListPage(HttpServletRequest request, Integer pageNo, Integer pageSize) {
		PageDto<CategorySecond> pageDto = new PageDto<>();
		List<CategorySecond> csList = categorySecondService.findCsListPage(pageNo - 1, pageSize);
		Integer count = categorySecondService.findCount();
		pageDto.setList(csList);
		pageDto.setTotalCount(count);

		Map<String, Object> pageMap = new HashMap<>();
		pageMap.put("totalCount", pageDto.getTotalCount());

		List<AdminCategorySecondDto> adminCategorySecondDtos = new ArrayList<>();
		// 将页面需要的信息封装到dto
		for (CategorySecond categorySecond : pageDto.getList()) {
			AdminCategorySecondDto adminCategorySecondDto = new AdminCategorySecondDto();
			adminCategorySecondDto.setCid(categorySecond.getCategory().getCid());
			adminCategorySecondDto.setCname(categorySecond.getCategory().getCname());
			adminCategorySecondDto.setCsid(categorySecond.getCsid());
			adminCategorySecondDto.setCsname(categorySecond.getCsname());
			adminCategorySecondDtos.add(adminCategorySecondDto);
		}
		pageMap.put("list", adminCategorySecondDtos);
		return pageMap;
	}

}
