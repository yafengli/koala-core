package cn.demo;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

@ManagedBean
@RequestScoped
public class HelloWorld {
	@ManagedProperty(name = "name", value = "@FUCK@")
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String action() {
		System.out.println("Hello World!");
		return "hello";
	}

	public String action2() {
		System.out.println("Hello World2!");
		this.setName("#FUCK#");
		return "hello";
	}
}
