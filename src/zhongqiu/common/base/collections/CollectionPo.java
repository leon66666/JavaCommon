package zhongqiu.common.base.collections;

public class CollectionPo {
	public Integer age;
	public Integer sex;
	public String name;

	public CollectionPo() {

	}

	public CollectionPo(Integer sex, String name, Integer age) {
		this.name = name;
		this.sex = sex;
		this.age = age;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
