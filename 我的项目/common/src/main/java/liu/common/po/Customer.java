package liu.common.po;

public class Customer {

	private String name;
	private String sex;
	private int height;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public Customer(String name, String sex, int height) {
		super();
		this.name = name;
		this.sex = sex;
		this.height = height;
	}
	public Customer() {
		super();
	}
	@Override
	public String toString() {
		return "Customer [name=" + name + ", sex=" + sex + ", height=" + height + "]";
	}
	
}
