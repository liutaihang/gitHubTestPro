package com.java1234.entity.auth;

public class PResource {
	private int id;
	private String url;//资源地址
	private String remark; //备注
	private String description;//描述
	private String createName;//创建人名字
	private Integer pid;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getCreateName() {
		return createName;
	}
	public void setCreateName(String createName) {
		this.createName = createName;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Integer getPid() {
		return pid;
	}
	public void setPid(Integer pid) {
		this.pid = pid;
	}
	@Override
	public String toString() {
		return "PResource [id=" + id + ", url="
				+ url + ", remark=" + remark + ", description=" + description
				+ ", createName=" + createName + ", pid=" + pid + "]";
	}
	
}
