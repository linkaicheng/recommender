package com.cheng.mall.bean;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * 一级分类的实体类对象
 *
 */
public class Category implements Serializable {
	private Integer cid;
	private String cname;
	// 一级分类中存放二级分类的集合:
	private Set<CategorySecond> categorySeconds = new HashSet<CategorySecond>();

	public Integer getCid() {
		return cid;
	}

	public void setCid(Integer cid) {
		this.cid = cid;
	}

	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	public Set<CategorySecond> getCategorySeconds() {
		return categorySeconds;
	}

	public void setCategorySeconds(Set<CategorySecond> categorySeconds) {
		this.categorySeconds = categorySeconds;
	}

	/**
	 * 为测试方便，重写toString
	 */
	@Override
	public String toString() {
		return "Category [cid=" + cid + ", cname=" + cname + ", categorySeconds=" + categorySeconds + "]";
	}

}
