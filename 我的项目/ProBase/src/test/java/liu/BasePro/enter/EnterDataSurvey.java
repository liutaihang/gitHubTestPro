package liu.BasePro.enter;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;
/**
 * 资料调查表
 */
@Document(collection = "lyc_EnterDataSurvey")
public class EnterDataSurvey implements
		Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3664050710471590726L;
	private Integer loanId;
	/**资料核查*/
	private List<LoanDataCheck> loanDataChecks;
	/**企业及相关人员基本情况*/
	private List<EnterAndPersonExplain> enterAndPersonExplains;
	/**银行存款*/
	private EnterBankDeposit enterBankDeposit;
	/**现金*/
	private EnterCash enterCash;
	/**结息*/
	private EnterInterest enterInterest;
	/**收入计算电核记录*/
	private EnterIncome enterIncome;
	/**目前履约车及相关收入与测算*/
	private EnterCars enterCars;
	/**公司财务状况分析*/
	private List<EnterFinanAnal> enterFinanAnals;
	/**资产备用文件*/
	private EnterAssets enterAssets;
	/**应付财务审核文件*/
	private EnterPayableFinan enterPayableFinan;
	/**短/长期借款类型审核文件*/
	private EnterLoan enterLoan;
	/**风险分析及意见*/
	private List<EnterRiskAnalsis> enterRiskAnalsis;
	/**审批意见*/
	private String auditOpinions;
	
	
	public Integer getLoanId() {
		return loanId;
	}
	public void setLoanId(Integer loanId) {
		this.loanId = loanId;
	}
	public List<LoanDataCheck> getLoanDataChecks() {
		return loanDataChecks;
	}
	public void setLoanDataChecks(List<LoanDataCheck> loanDataChecks) {
		this.loanDataChecks = loanDataChecks;
	}
	public List<EnterAndPersonExplain> getEnterAndPersonExplains() {
		return enterAndPersonExplains;
	}
	public void setEnterAndPersonExplains(
			List<EnterAndPersonExplain> enterAndPersonExplains) {
		this.enterAndPersonExplains = enterAndPersonExplains;
	}
	public EnterBankDeposit getEnterBankDeposit() {
		return enterBankDeposit;
	}
	public void setEnterBankDeposit(EnterBankDeposit enterBankDeposit) {
		this.enterBankDeposit = enterBankDeposit;
	}
	public EnterCash getEnterCash() {
		return enterCash;
	}
	public void setEnterCash(EnterCash enterCash) {
		this.enterCash = enterCash;
	}
	public EnterInterest getEnterInterest() {
		return enterInterest;
	}
	public void setEnterInterest(EnterInterest enterInterest) {
		this.enterInterest = enterInterest;
	}
	public EnterIncome getEnterIncome() {
		return enterIncome;
	}
	public void setEnterIncome(EnterIncome enterIncome) {
		this.enterIncome = enterIncome;
	}
	public EnterCars getEnterCars() {
		return enterCars;
	}
	public void setEnterCars(EnterCars enterCars) {
		this.enterCars = enterCars;
	}
	public List<EnterFinanAnal> getEnterFinanAnals() {
		return enterFinanAnals;
	}
	public void setEnterFinanAnals(List<EnterFinanAnal> enterFinanAnals) {
		this.enterFinanAnals = enterFinanAnals;
	}
	public EnterAssets getEnterAssets() {
		return enterAssets;
	}
	public void setEnterAssets(EnterAssets enterAssets) {
		this.enterAssets = enterAssets;
	}
	public EnterPayableFinan getEnterPayableFinan() {
		return enterPayableFinan;
	}
	public void setEnterPayableFinan(EnterPayableFinan enterPayableFinan) {
		this.enterPayableFinan = enterPayableFinan;
	}
	public EnterLoan getEnterLoan() {
		return enterLoan;
	}
	public void setEnterLoan(EnterLoan enterLoan) {
		this.enterLoan = enterLoan;
	}
	public List<EnterRiskAnalsis> getEnterRiskAnalsis() {
		return enterRiskAnalsis;
	}
	public void setEnterRiskAnalsis(List<EnterRiskAnalsis> enterRiskAnalsis) {
		this.enterRiskAnalsis = enterRiskAnalsis;
	}
	public String getAuditOpinions() {
		return auditOpinions;
	}
	public void setAuditOpinions(String auditOpinions) {
		this.auditOpinions = auditOpinions;
	}
	@Override
	public String toString() {
		return "EnterDataSurvey [loanId=" + loanId + ", loanDataChecks=" + loanDataChecks + ", enterAndPersonExplains="
				+ enterAndPersonExplains + ", enterBankDeposit=" + enterBankDeposit + ", enterCash=" + enterCash
				+ ", enterInterest=" + enterInterest + ", enterIncome=" + enterIncome + ", enterCars=" + enterCars
				+ ", enterFinanAnals=" + enterFinanAnals + ", enterAssets=" + enterAssets + ", enterPayableFinan="
				+ enterPayableFinan + ", enterLoan=" + enterLoan + ", enterRiskAnalsis=" + enterRiskAnalsis
				+ ", auditOpinions=" + auditOpinions + "]";
	}

}
