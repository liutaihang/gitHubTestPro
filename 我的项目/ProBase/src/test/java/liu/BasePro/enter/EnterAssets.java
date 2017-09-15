package liu.BasePro.enter;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
/**
 * 资料调查表 - 资产备用文件
 */
public class EnterAssets implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1276863851045611441L;
	/**车辆*/
	private List<Map<String, String>> assetsMaps;
	/**备注*/
	private String remark;

	public List<Map<String, String>> getAssetsMaps() {
		return assetsMaps;
	}
	public void setAssetsMaps(List<Map<String, String>> assetsMaps) {
		this.assetsMaps = assetsMaps;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	@Override
	public String toString() {
		return "EnterAssets [assetsMaps=" + assetsMaps + ", remark=" + remark + "]";
	}
	
	
}
