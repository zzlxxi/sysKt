package cn.w2site.fik.systk.entity;

import org.springframework.data.elasticsearch.annotations.Document;

@Document(type = "product", indexName = "t_user")
public class UserEntity2 {

	private String id;
	private String name;
	private String age;

	public UserEntity2() {
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

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

}
