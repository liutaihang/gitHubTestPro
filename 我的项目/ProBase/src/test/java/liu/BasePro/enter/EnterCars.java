package liu.BasePro.enter;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
/**
 * 资料调查表 - 目前履约车及相关收入与测算
 */
public class EnterCars implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2813685069184737887L;
	/**车辆*/
	private List<Map<String, String>> carMaps;
	/**备注*/
	private String remark;

	public List<Map<String, String>> getCarMaps() {
		return carMaps;
	}
	public void setCarMaps(List<Map<String, String>> carMaps) {
		this.carMaps = carMaps;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	@Override
	public String toString() {
		return "EnterCars [carMaps=" + carMaps + ", remark=" + remark + "]";
	}
	
}
