package liu.BasePro;

import java.util.Map;

public class TestBean {
	
	private int id;
	private Map<String, Object> requestMap;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Map<String, Object> getRequestMap() {
		return requestMap;
	}
	public void setRequestMap(Map<String, Object> requestMap) {
		this.requestMap = requestMap;
	}
	@Override
	public String toString() {
		return "TestBean [id=" + id + ", requestMap=" + requestMap + "]";
	}
	public TestBean(int id, Map<String, Object> requestMap) {
		super();
		this.id = id;
		this.requestMap = requestMap;
	}
	public TestBean() {
		super();
	}
	
}
