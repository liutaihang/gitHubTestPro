package liu.po;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Id;

public class BasePo {

	@Id
	private String id;
	
	@Column(name = "createTime")
	private Date createTime;

	@Column(name = "updateTime")
	private Date updateTime;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	
}
