// package com.cheng.mall.service;
//
// import java.util.List;
//
// import org.junit.runner.RunWith;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.test.context.SpringBootTest;
// import org.springframework.test.context.junit4.SpringRunner;
//
// import com.cheng.mall.bean.Category;
//
// @SpringBootTest
// @RunWith(SpringRunner.class)
// public class CategoryServiceTest {
// @Autowired
// private CategoryService categoryService;
//
// // @Test
// public void testSaveCategory() {
// Category category = new Category();
// category.setCname("test2");
// categoryService.saveCategory(category);
// categoryService.deleteCategory(category.getCid());
//
// }
//
// // @Test
// public void testSaveCategoryBlank() {
// Category category = new Category();
// category.setCname("");
// categoryService.saveCategory(category);
//
// }
//
// // @Test
// public void testDeleteCategory() {
// Category category = new Category();
// category.setCname("test2");
// categoryService.saveCategory(category);
// categoryService.deleteCategory(category.getCid());
// }
//
// // @Test
// public void testUpdateCategory() {
// Category category = new Category();
// category.setCid(14);
// category.setCname("change");
// categoryService.updateCategory(category);
// }
//
// // @Test
// public void testFindAllCategory() {
// List<Category> list = categoryService.findAllCategory();
// System.out.println(list);
// }
//
// }
