package liu.po;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class People {
	
	@Id
	String id;
	String name;
	String sex;
	Like like;
	
	
	public People(String name, String sex, Like like) {
		super();
		this.name = name;
		this.sex = sex;
		this.like = like;
	}

	public People() {
		super();
	}


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


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


	public Like getLike() {
		return like;
	}


	public void setLike(Like like) {
		this.like = like;
	}


	@Override
	public String toString() {
		return "People [id=" + id + ", name=" + name + ", sex=" + sex + ", like=" + like + "]";
	}


}

