package liu.BasePro.enter;
/**
 * 资料调查表-公司财务状况分析 类型
 * */
public enum EnterFinaPosition {
	/**申请及运营车辆成本*/
	OPERATE_COSTS("申请及运营车辆成本"),
	/**人力成本*/
	HUMAN_COSTS("人力成本"),
	/**场地成本*/
	SITE_COSTS("场地成本"),
	/**其他负债*/
	DEBT("其他负债"),
	;
	
   String value;
   
   private EnterFinaPosition(String value){
	   this.value = value;
   }

	public String getValue() {
		return value;
	}	
}
