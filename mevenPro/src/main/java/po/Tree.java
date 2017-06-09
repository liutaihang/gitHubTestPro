package po;

import java.io.Serializable;
import java.math.BigDecimal;

public class Tree implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3869114665034422949L;
	private int id;	//Ψһ��ʶ
	private String name;	//����
	private BigDecimal price;	//�Ӹ�
	private String type;	//����
	
	
	public Tree() {
		super();
	}
	public Tree(String name, BigDecimal price, String type) {
		super();
		this.name = name;
		this.price = price;
		this.type = type;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	@Override
	public String toString() {
		return "Tree [id=" + id + ", name=" + name + ", price=" + price + ", type=" + type + "]";
	}
	
}
