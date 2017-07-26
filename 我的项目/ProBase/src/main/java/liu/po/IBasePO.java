package liu.po;

import java.util.Date;


public interface IBasePO {

	public static final String serialNo = "serialNo";
	public static final String createTime = "createTime";
	public static final String lastUpdateTime = "lastUpdateTime";
	public static final String lastOperatorName = "lastOperatorName";
	public static final String lastOperatorId = "lastOperatorId";
	
	public String getSerialNo() ;

	public Date getCreateTime() ;

	public Date getLastUpdateTime() ;

	public String getLastOperatorName() ;

	public Integer getLastOperatorId() ;

	public Integer getId() ;

}
