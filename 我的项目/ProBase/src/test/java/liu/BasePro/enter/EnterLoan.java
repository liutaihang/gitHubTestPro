package liu.BasePro.enter;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
/**
 * 资料调查表 - 短/长期借款类审核文件
 */
public class EnterLoan implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4769803605105102456L;

	/**借款*/
	private List<Map<String, String>> loanMaps;
	/**备注*/
	private String remark;

	public List<Map<String, String>> getLoanMaps() {
		return loanMaps;
	}
	public void setLoanMaps(List<Map<String, String>> loanMaps) {
		this.loanMaps = loanMaps;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	@Override
	public String toString() {
		return "EnterLoan [loanMaps=" + loanMaps + ", remark=" + remark + "]";
	}
	
}
