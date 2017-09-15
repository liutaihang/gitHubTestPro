package liu.BasePro.enter;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
/**
 * 资料调查表 - 现金
 * */
public class EnterCash implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 126004757733032538L;
	/**现金*/
	private List<Map<String, String>> cashMaps;
	/**备注*/
	private String remark;

	public List<Map<String, String>> getCashMaps() {
		return cashMaps;
	}
	public void setCashMaps(List<Map<String, String>> cashMaps) {
		this.cashMaps = cashMaps;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	@Override
	public String toString() {
		return "EnterCash [cashMaps=" + cashMaps + ", remark=" + remark + "]";
	}
	
	
	
}
