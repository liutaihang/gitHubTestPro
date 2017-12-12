package com.java1234.Vo;

import com.java1234.entity.auth.PResource;
import com.java1234.entity.auth.Role;

/**
 * 初始化设置列表树
 * @Title: ResourceVo.java 
 * @Package com.java1234.Vo 
 * @Description: TODO(用一句话描述该文件做什么) 
 * @author liuth 
 * @date Oct 21, 2017 7:18:25 PM 
 * @version V1.0
 */
public class TreeVo {
	private Integer id;
	private Integer pid;
	private String text;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getPid() {
		return pid;
	}
	public void setPid(Integer pid) {
		this.pid = pid;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public TreeVo() {
		super();
	}
	public TreeVo(PResource pResource) {
		this.id = pResource.getId();
		this.pid = pResource.getPid();
		this.text = pResource.getDescription();
	}
	
	public TreeVo(Role role) {
		super();
		this.id = role.getId();
		this.text = role.getNickName();
	}
	
}
