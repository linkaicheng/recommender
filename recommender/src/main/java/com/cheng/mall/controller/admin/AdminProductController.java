package com.cheng.mall.controller.admin;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.cheng.mall.bean.CategorySecond;
import com.cheng.mall.bean.Product;
import com.cheng.mall.dto.AddProductDto;
import com.cheng.mall.service.CategorySecondService;
import com.cheng.mall.service.ProductService;
import com.cheng.mall.util.Message;

@RestController
@RequestMapping("/admin")
public class AdminProductController {
	@Resource
	private ProductService productService;
	@Resource
	private CategorySecondService categorySecondService;

	/**
	 * 返回所有商品
	 * 
	 * @author linkaicheng
	 * @date 2017年4月9日 下午5:27:20
	 * @return
	 *
	 */
	@RequestMapping(value = { "/getProductList" }, method = RequestMethod.GET)
	public List<AddProductDto> getProductList() {
		List<Product> products = productService.findAllProduct();
		List<AddProductDto> addProductDtos = new ArrayList<>();
		for (Product product : products) {
			AddProductDto addProductDto = new AddProductDto();
			BeanUtils.copyProperties(product, addProductDto);
			addProductDto.setCsid(product.getCategorySecond().getCsid());
			addProductDto.setCsname(product.getCategorySecond().getCsname());
			addProductDtos.add(addProductDto);

		}
		return addProductDtos;
	}

	/**
	 * 上传商品图片
	 * 
	 * @author linkaicheng
	 * @date 2017年4月9日 下午5:29:22
	 * @param product
	 * @return
	 *
	 */
	@RequestMapping(value = { "/uploadImage" }, method = RequestMethod.POST)
	public Message uploadImage(@RequestParam("file") MultipartFile file, HttpServletRequest request) {
		Message message = new Message();
		BufferedOutputStream out = null;
		if (!file.isEmpty()) {
			try {
				out = new BufferedOutputStream(new FileOutputStream(
						new File(request.getServletContext().getRealPath("/WEB-INF/static/img/products") + "//"
								+ file.getOriginalFilename())));
				message.setParam(file.getOriginalFilename());
				out.write(file.getBytes());
				out.flush();
				out.close();
			} catch (Exception e) {
				// "上传失败,"
				message.setInfo("faile");
			} finally {
				if (out != null) {
					try {
						out.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
			// "上传成功";
			message.setInfo("success");
		} else {
			// "上传失败，因为文件是空的.";
			message.setInfo("faile because mpty");
		}
		return message;

	}

	/**
	 * 添加或修改商品
	 * 
	 * @author linkaicheng
	 * @date 2017年4月10日 下午2:47:06
	 * @param productDto
	 * @return
	 *
	 */
	@RequestMapping(value = { "/addProduct" }, method = RequestMethod.POST)
	public List<AddProductDto> addOrUpdateProduct(AddProductDto productDto) {
		String imagePath = null;
		if (productDto != null && productDto.getImage() != null) {
			imagePath = "../static/img/products/" + productDto.getImage();
		}

		Product product = new Product();
		if (productDto.getPid() != null) {// pid不为空，做更新操作
			product = productService.findProductByPid(productDto.getPid());
		}
		BeanUtils.copyProperties(productDto, product);
		if (imagePath != null) {
			product.setImage(imagePath);

		}
		CategorySecond categorySecond = categorySecondService.findCategorySecondByCsid(productDto.getCsid());
		product.setCategorySecond(categorySecond);
		productService.addProduct(product);
		// 返回商品展示页面所需信息
		List<Product> products = productService.findAllProduct();
		List<AddProductDto> addProductDtos = new ArrayList<>();
		for (Product product2 : products) {
			AddProductDto addProductDto = new AddProductDto();
			BeanUtils.copyProperties(product2, addProductDto);
			addProductDto.setCsid(product2.getCategorySecond().getCsid());
			addProductDto.setCsname(product2.getCategorySecond().getCsname());
			addProductDtos.add(addProductDto);

		}
		return addProductDtos;
	}

	/**
	 * 删除商品
	 * 
	 * @author linkaicheng
	 * @date 2017年4月9日 下午5:29:22
	 * @param product
	 * @return
	 *
	 */
	@RequestMapping(value = { "/deleteProduct" }, method = RequestMethod.POST)
	public List<Product> deleteProduct(Integer pid, HttpServletRequest request) {
		if (pid != null) {
			Product product = productService.findProductByPid(pid);
			// 删除商品的图片
			String[] path = product.getImage().split("/");
			File file = new File(request.getServletContext().getRealPath("/WEB-INF/static/img/products") + "//"
					+ path[path.length - 1]);
			if (file.exists()) {
				file.delete();
			}
			productService.deleteProduct(pid);
		}
		return productService.findAllProduct();
	}

}
