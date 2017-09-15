package liu.BasePro.enter;
/**
 * 资料调查表-资料核查 类型
 * */
public enum LoanDataCheckType {
	/**人法网*/
	PEOPLE_NET("人法网"),
	/**工商网*/
	BUSINESS_NET("工商网"),
	/**天行*/
	SKY_WALKS("天行"),
	/**数据路由*/
	DATA_ROUTE("数据路由"),
	/**鹏元征信*/
	PY_CREDIT("鹏元征信"),
	/**122驾照积分*/
	DRIVER_POINTS_122("122驾照积分"),
	;
	
   String value;
   
   private LoanDataCheckType(String value){
	   this.value = value;
   }

	public String getValue() {
		return value;
	}	   
}
