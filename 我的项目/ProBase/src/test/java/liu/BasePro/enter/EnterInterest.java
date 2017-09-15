	package liu.BasePro.enter;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
/**
 * 资料调查表 - 结息
 * */
public class EnterInterest implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6720614998502226952L;

	/**结息*/
	private List<Map<String, String>> interestMaps;
	/**备注*/
	private String remark;

	public List<Map<String, String>> getInterestMaps() {
		return interestMaps;
	}
	public void setInterestMaps(List<Map<String, String>> interestMaps) {
		this.interestMaps = interestMaps;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	@Override
	public String toString() {
		return "EnterInterest [interestMaps=" + interestMaps + ", remark=" + remark + "]";
	}
	
	
}
