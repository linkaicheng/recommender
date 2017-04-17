package com.cheng.mall.dto;

import java.util.LinkedHashSet;
import java.util.Set;

import com.cheng.mall.bean.CategorySecond;

/**
 * 分类导航类，封装了每个一级分类下的二级分类
 * 
 * @author linkaicheng
 * @date 2017年4月16日 上午1:11:23
 *
 */
public class CategoryNavigationDto {
	private String cname;
	private Integer cid;
	private Set<CategorySecond> csSet = new LinkedHashSet<>();

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

	public Set<CategorySecond> getCsSet() {
		return csSet;
	}

	public void setCsSet(Set<CategorySecond> csSet) {
		this.csSet = csSet;
	}

	@Override
	public String toString() {
		return "CategoryNavigationDto [cname=" + cname + ", cid=" + cid + ", csSet=" + csSet + "]";
	}

}
