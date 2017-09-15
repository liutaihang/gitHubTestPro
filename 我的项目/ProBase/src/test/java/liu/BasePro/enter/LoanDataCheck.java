package liu.BasePro.enter;

import java.io.Serializable;
import java.util.Map;
/**
 * 资料调查表 - 资料审核
 * */
public class LoanDataCheck implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3271153866632093319L;
	/**标签名称*/
	private String labelName;
	/**附件  <上传文件名,存储路径>*/
	private Map<String, String> attchMap;
	/**备注*/
	private String remark;
	
	
	public String getLabelName() {
		return labelName;
	}
	public void setLabelName(String labelName) {
		this.labelName = labelName;
	}
	public Map<String, String> getAttchMap() {
		return attchMap;
	}
	public void setAttchMap(Map<String, String> attchMap) {
		this.attchMap = attchMap;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	@Override
	public String toString() {
		return "LoanDataCheck [labelName=" + labelName + ", attchMap=" + attchMap + ", remark=" + remark + "]";
	}
	
	
}
