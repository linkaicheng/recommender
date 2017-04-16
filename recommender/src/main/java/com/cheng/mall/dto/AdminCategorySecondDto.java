package com.cheng.mall.dto;

/**
 * 传递给后台二级分类管理页面的对象
 * 
 * @author linkaicheng
 * @date 2017年4月11日 下午6:04:59
 *
 */
public class AdminCategorySecondDto {
	private Integer csid;
	private String csname;
	private Integer cid;
	private String cname;

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

}
