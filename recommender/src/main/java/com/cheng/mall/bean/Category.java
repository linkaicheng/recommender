package com.cheng.mall.bean;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * 一级分类的实体类对象
 *
 */
@Table(name = "category")
@Entity
public class Category implements Serializable {
	private Integer cid;
	private String cname;
	// 一级分类中存放二级分类的集合:
	private Set<CategorySecond> categorySeconds = new HashSet<CategorySecond>();

	@GeneratedValue
	@Id
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

	// 映射单向1-n的关联关系
	// 使用@OneToMany来映射1-n的关联关系
	// 使用@joinColumn来映射外键列的名称
	@JoinColumn(name = "cid")
	@OneToMany(fetch = FetchType.EAGER, cascade = { CascadeType.REMOVE })
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
