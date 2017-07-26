package liu.po.cache;

import java.util.Date;

import org.apache.commons.lang3.StringUtils;


public class RequestParam {
	private String loginId;

	private String loginName;
	
	private String operationNum;

	private Date lashtUpdateTime;

	public RequestParam() {
		super();
	}

	public RequestParam(String loginId, String loginName, String operationNum, Date lashtUpdateTime) {
		super();
		this.loginId = loginId;
		this.loginName = loginName;
		this.operationNum = operationNum;
		this.lashtUpdateTime = lashtUpdateTime;
	}

	public String getLoginId() {
		return StringUtils.isBlank(loginId) ? "1" : loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	public String getLoginName() {
		return StringUtils.isBlank(loginName) ? "administrator" : loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
		
	}

	public String getOperationNum() {
		return StringUtils.isBlank(operationNum) ? "" : operationNum;
	}

	public void setOperationNum(String operationNum) {
		this.operationNum = operationNum;
	}

	public Date getLashtUpdateTime() {
		return null == lashtUpdateTime ? new Date() : lashtUpdateTime;
	}

	public void setLashtUpdateTime(Date lashtUpdateTime) {
		this.lashtUpdateTime = lashtUpdateTime;
	}

}
