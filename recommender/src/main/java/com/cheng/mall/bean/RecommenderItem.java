package com.cheng.mall.bean;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

@Table(name = "recommenderitem")
@Entity
public class RecommenderItem {
	private Integer recommenderItemId;
	private Float score;
	// 商品外键:对象
	private Product product;
	// 用户外键:对象
	private User user;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date recommenderDate;

	@GeneratedValue
	@Id
	public Integer getRecommenderItemId() {
		return recommenderItemId;
	}

	public void setRecommenderItemId(Integer recommenderItemId) {
		this.recommenderItemId = recommenderItemId;
	}

	// 映射单向 n-1 的关联关系
	// 使用 @ManyToOne 来映射多对一的关联关系
	// 使用 @JoinColumn 来映射外键.
	// 可使用 @ManyToOne 的 fetch 属性来修改默认的关联属性的加载策略
	@JoinColumn(name = "pid")
	@ManyToOne(fetch = FetchType.EAGER)
	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	// 映射单向 n-1 的关联关系
	// 使用 @ManyToOne 来映射多对一的关联关系
	// 使用 @JoinColumn 来映射外键.
	// 可使用 @ManyToOne 的 fetch 属性来修改默认的关联属性的加载策略
	@JoinColumn(name = "uid")
	@ManyToOne(fetch = FetchType.EAGER)
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Date getRecommenderDate() {
		return recommenderDate;
	}

	public void setRecommenderDate(Date recommenderDate) {
		this.recommenderDate = recommenderDate;
	}

	public Float getScore() {
		return score;
	}

	public void setScore(Float score) {
		this.score = score;
	}

	@Override
	public String toString() {
		return "RecommenderItem [recommenderItemId=" + recommenderItemId + ", score=" + score + ", product=" + product
				+ ", user=" + user + ", recommenderDate=" + recommenderDate + "]";
	}

}
