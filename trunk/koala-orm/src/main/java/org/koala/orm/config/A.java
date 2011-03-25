package org.koala.orm.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.ImportResource;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

@Component("a")
@ImportResource("classpath:./META-INF/spring/properties-config.xml")
public class A {
	@Resource(name = "aInject")
	private AInject aInject;
	@Value("#{testp[a_one]}")
	private String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public AInject getaInject() {
		return aInject;
	}

	public void setaInject(AInject aInject) {
		this.aInject = aInject;
	}

	@PostConstruct
	public void initA() {
		System.out.printf("A init do. [%s]\n", this.getMessage());
	}
}
