package liu.enums;

public enum WhatType {
	
	what("s");
	private String value;

	public String getValue() {
		return value;
	}

	private WhatType(String value) {
		this.value = value;
	}
}
