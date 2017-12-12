package com.java1234.Vo;

import com.java1234.Vo.searchVo.UserTransSearchVo;
/**
 * 导出page
 *
 */
public class ExportPageVo extends UserTransSearchVo{
	private Integer[] pageNo;
	private Integer pageSize;
	private Integer startNum;
	private String url;


	
	public Integer[] getPageNo() {
		return pageNo;
	}

	public void setPageNo(Integer[] pageNo) {
		this.pageNo = pageNo;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getStartNum() {
		return startNum;
	}

	public void setStartNum(Integer startNum) {
		this.startNum = startNum;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	
	
}
