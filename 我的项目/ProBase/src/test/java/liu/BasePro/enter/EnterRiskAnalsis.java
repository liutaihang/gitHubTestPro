package liu.BasePro.enter;

import java.io.Serializable;
/**
 * 资料调查表 - 风险分析及意见
 */
public class EnterRiskAnalsis implements
		Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6278965835174202798L;
	/**风险分析及意见*/
	private LoanRiskAnalsis loanRiskAnalsis;
	/**信息*/
	private String info;
	/**备注*/
	private String remark;
	
	public LoanRiskAnalsis getLoanRiskAnalsis() {
		return loanRiskAnalsis;
	}
	public void setLoanRiskAnalsis(LoanRiskAnalsis loanRiskAnalsis) {
		this.loanRiskAnalsis = loanRiskAnalsis;
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
		return "EnterRiskAnalsis [loanRiskAnalsis=" + loanRiskAnalsis + ", info=" + info + ", remark=" + remark + "]";
	}
	
	
}
