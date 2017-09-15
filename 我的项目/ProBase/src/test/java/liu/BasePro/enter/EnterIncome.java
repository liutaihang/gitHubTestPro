package liu.BasePro.enter;

import java.io.Serializable;
/**
 * 资料调查表 - 收入计算电核记录
 * */
public class EnterIncome implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1888136940813878354L;
	/**信息*/
	private String info;
	/**备注*/
	private String remark;

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
		return "EnterIncome [info=" + info + ", remark=" + remark + "]";
	}
	
	
}
