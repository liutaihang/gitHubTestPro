package liu.enums;

public enum AgreementType {

	thiss("ss"),
	zaza("渣渣");
	
	private String value;
	
	private AgreementType(String value) {
		this.value = value;
	}
	public String getValue(){
		return this.value;
	}
}
