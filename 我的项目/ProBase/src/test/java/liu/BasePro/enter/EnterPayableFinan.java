package liu.BasePro.enter;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
/**
 * 资料调查表 - 应付财务审核文件
 */
public class EnterPayableFinan  implements
		Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 583213952753492999L;
	/**应付财务*/
	private List<Map<String, String>> finanMaps;
	/**备注*/
	private String remark;

	public List<Map<String, String>> getFinanMaps() {
		return finanMaps;
	}
	public void setFinanMaps(List<Map<String, String>> finanMaps) {
		this.finanMaps = finanMaps;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	@Override
	public String toString() {
		return "EnterPayableFinan [finanMaps=" + finanMaps + ", remark=" + remark + "]";
	}
	
}
