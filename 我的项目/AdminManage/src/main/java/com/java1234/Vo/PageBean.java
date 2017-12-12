package com.java1234.Vo;

public class PageBean {
	/**每页显示数量*/
	private Integer pageSize = 10;
	/**所选页数*/
	private Integer page;
	/**开始下标*/
	private Integer startNo;
	
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	public Integer getStartNo() {
		startNo = (page - 1) * pageSize;
		return startNo;
	}
	public void setStartNo(Integer startNo) {
		this.startNo = startNo;
	}
	public Integer getPage() {
		return page;
	}
	public void setPage(Integer page) {
		this.page = page;
	}
	
	
}
