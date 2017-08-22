package liu.po.msq;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import liu.po.BasePo;
@Entity
@Table(name = "animal")
public class Animal extends BasePo implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3487463812451018927L;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "type")
	private String type;
	
	@Column(name = "habit")
	private String habit;
	
	@Column(name = "food")
	private String food;
	
	@Column(name = "environment")
	private String environment;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getHabit() {
		return habit;
	}
	public void setHabit(String habit) {
		this.habit = habit;
	}
	public String getFood() {
		return food;
	}
	public void setFood(String food) {
		this.food = food;
	}
	public String getEnvironment() {
		return environment;
	}
	public void setEnvironment(String environment) {
		this.environment = environment;
	}
	public Animal(String name, String type, String habit, String food, String environment) {
		super();
		this.name = name;
		this.type = type;
		this.habit = habit;
		this.food = food;
		this.environment = environment;
	}
	public Animal() {
		super();
	}
	
}
