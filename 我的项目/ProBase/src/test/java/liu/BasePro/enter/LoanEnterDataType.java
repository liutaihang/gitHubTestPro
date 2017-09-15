package liu.BasePro.enter;
/**
 * 资料调查表-企业及相关人员基本情况 类型
 * */
public enum LoanEnterDataType {
	/**法人*/
	LEGAL_PERSON("法人"),
	/**实际控股人*/
	SHAREHOLDER_HOLD("实际控股人"),
	/**会计*/
	BOOK_KEEPER("会计"),
	/**股东1*/
	SHAREHOLDER_ONE("股东1"),
	;
	
   String value;
   
   private LoanEnterDataType(String value){
	   this.value = value;
   }

	public String getValue() {
		return value;
	}	
}
