package liu.BasePro.enter;
/**
 * 资料调查表-风险分析及意见
 * */
public enum LoanRiskAnalsis {
	/**公司优势*/
	COMPANY_ADVANTAGE("公司优势"),
	/**公司劣势*/
	COMPANY_DISADVANTAGE("公司劣势"),
	/**行业优势*/
	INDUSTRY_ADVANTAGE("行业优势"),
	/**还款来源分析*/
	REPAY_SOURCE("还款来源分析"),
	/**相关风险*/
	RELEVANT_RISK("相关风险"),
	;

   String value;
   
   private LoanRiskAnalsis(String value){
	   this.value = value;
   }

	public String getValue() {
		return value;
	}	
}
