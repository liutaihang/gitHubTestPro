package liu.BasePro.enter;

import java.io.Serializable;
/**
 * 资料调查表 - 公司财务状况分析
 */
public class EnterFinanAnal implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7447714064198568843L;
	/**公司财务状况类型*/
	private EnterFinaPosition enterFinaPosition;
	/**信息*/
	private String info;
	/**备注*/
	private String remark;

	public EnterFinaPosition getEnterFinaPosition() {
		return enterFinaPosition;
	}
	public void setEnterFinaPosition(EnterFinaPosition enterFinaPosition) {
		this.enterFinaPosition = enterFinaPosition;
	}
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	@Override
	public String toString() {
		return "EnterFinanAnal [enterFinaPosition=" + enterFinaPosition + ", info=" + info + ", remark=" + remark + "]";
	}
	
}
