package cn.demo;

import java.util.Enumeration;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

@ManagedBean
@RequestScoped
public class HelloWorld {
	@ManagedProperty(name = "name", value = "@FUCK@")
	private String name;

	private Long id;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String action() {
		System.out.printf("[%s][%s,%s]\n", "Hello World!", this.getName(), this
				.getId());
		HttpServletRequest req = (HttpServletRequest) FacesContext
				.getCurrentInstance().getExternalContext().getRequest();

		for (Enumeration<String> env = req.getParameterNames(); env
				.hasMoreElements();) {
			String key = env.nextElement();
			System.out.printf("[%s=%s]\n", key, req.getParameter(key));
		}
		return "hello";
	}

	public String action2() {
		System.out.println("Hello World2!");
		this.setName("#FUCK#");
		return "hello";
	}
}
