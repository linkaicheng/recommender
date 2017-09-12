// package com.cheng.mall.service.admin;
//
// import static org.junit.Assert.fail;
//
// import java.util.Date;
//
// import org.junit.Test;
// import org.junit.runner.RunWith;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.test.context.SpringBootTest;
// import org.springframework.test.context.junit4.SpringRunner;
//
// import com.cheng.mall.bean.CategorySecond;
// import com.cheng.mall.bean.Product;
// import com.cheng.mall.service.CategorySecondService;
// import com.cheng.mall.service.ProductService;
//
// @SpringBootTest
// @RunWith(SpringRunner.class)
// public class ProductServiceTest {
// @Autowired
// private ProductService productService;
// @Autowired
// private CategorySecondService categorySecondService;
//
// @Test
// public void testFindNew() {
// for (Product p : productService.findNew()) {
// System.out.println(p.getPid());
// }
// }
//
// // @Test
// public void testFindHot() {
// for (Product p : productService.findHot()) {
// System.out.println(p.getPid());
// }
// }
//
// // @Test
// public void testFindAllProduct() {
// fail("Not yet implemented");
// }
//
// // @Test
// public void testAddProduct() {
// Product product = new Product();
// product.setImage("6666");
// product.setIs_hot(0);
// product.setMarket_price(20.0);
// product.setShop_price(10.0);
// product.setPdate(new Date());
// product.setPdesc("jjjj");
// product.setPname("aaa");
// CategorySecond cs = categorySecondService.findCategorySecondByCsid(11);
// product.setCategorySecond(cs);
// productService.addProduct(product);
// }
//
// // @Test
// public void testDeleteProduct() {
// productService.deleteProduct(3);
// }
//
// // @Test
// public void testUpdateProduct() {
// Product product = new Product();
// product.setPid(4);
// product.setImage("6666");
// product.setIs_hot(0);
// product.setMarket_price(20.0);
// product.setShop_price(10.0);
// product.setPdate(new Date());
// product.setPdesc("jjjj");
// product.setPname("88888888");
// CategorySecond cs = categorySecondService.findCategorySecondByCsid(11);
// product.setCategorySecond(cs);
// productService.updateProduct(product);
// }
//
// // @Test
// public void testFindProductByPid() {
// System.out.println(productService.findProductByPid(4));
// }
//
// }
