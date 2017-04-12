package com.cheng.mall.bean;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * 二级分类的实体
 *
 */
@Table(name = "categorysecond", uniqueConstraints = { @UniqueConstraint(columnNames = { "csname" }) })
@Entity
@JsonIgnoreProperties(value = { "category" })
public class CategorySecond implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer csid;
	private String csname;
	// 所属一级分类.存的是一级分类的对象.
	private Category category;
	// 配置商品集合

	private Set<Product> products;

	@GeneratedValue
	@Id
	public Integer getCsid() {
		return csid;
	}

	public void setCsid(Integer csid) {
		this.csid = csid;
	}

	public String getCsname() {
		return csname;
	}

	public void setCsname(String csname) {
		this.csname = csname;
	}

	// 映射单向 n-1 的关联关系
	// 使用 @ManyToOne 来映射多对一的关联关系
	// 使用 @JoinColumn 来映射外键.
	// 可使用 @ManyToOne 的 fetch 属性来修改默认的关联属性的加载策略
	// @ManyToOne(fetch = FetchType.EAGER, cascade = { CascadeType.REMOVE })
	@JoinColumn(name = "cid")
	@ManyToOne(fetch = FetchType.EAGER)
	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	@Override
	public String toString() {
		return "CategorySecond [csid=" + csid + ", csname=" + csname + ", category=" + category + "]";
	}

	// 映射单向1-n的关联关系
	// 使用@OneToMany来映射1-n的关联关系
	// 使用@joinColumn来映射外键列的名称
	@JoinColumn(name = "csid")
	@OneToMany(fetch = FetchType.EAGER, cascade = { CascadeType.REMOVE })
	public Set<Product> getProducts() {
		return products;
	}

	public void setProducts(Set<Product> products) {
		this.products = products;
	}

}
