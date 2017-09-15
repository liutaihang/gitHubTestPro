package liu.BasePro.enter;

import java.io.Serializable;
/**
 * 资料调查表 - 企业及相关人员基本情况
 * 
 * */
public class EnterAndPersonExplain implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3729335161912361811L;

	private LoanEnterDataType loanEnterDataType;
	/**详细情况*/
	private String details;
	
	public LoanEnterDataType getLoanEnterDataType() {
		return loanEnterDataType;
	}
	public void setLoanEnterDataType(LoanEnterDataType loanEnterDataType) {
		this.loanEnterDataType = loanEnterDataType;
	}
	public String getDetails() {
		return details;
	}
	public void setDetails(String details) {
		this.details = details;
	}
	@Override
	public String toString() {
		return "EnterAndPersonExplain [loanEnterDataType=" + loanEnterDataType + ", details=" + details + "]";
	}
	
	
}
