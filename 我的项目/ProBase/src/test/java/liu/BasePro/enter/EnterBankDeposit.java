package liu.BasePro.enter;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
/**
 * 资料调查表 - 银行存款
 * */
public class EnterBankDeposit implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3377130812363517494L;
	
	/**存款信息*/
	private List<Map<String, String>> depositMaps;
	/**备注*/
	private String remark;
	
	public List<Map<String, String>> getDepositMaps() {
		return depositMaps;
	}
	public void setDepositMaps(List<Map<String, String>> depositMaps) {
		this.depositMaps = depositMaps;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	@Override
	public String toString() {
		return "EnterBankDeposit [depositMaps=" + depositMaps + ", remark=" + remark + "]";
	}
	
	
}
