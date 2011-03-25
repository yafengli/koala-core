package org.koala.orm.config;

import org.springframework.stereotype.Service;

@Service("aInject")
public class AInject {
	private String injectName = "A inject.";

	public String getInjectName() {
		return injectName;
	}

	public void setInjectName(String injectName) {
		this.injectName = injectName;
	}

	/*other thing*/
	public void sayHello() {
		System.out.printf("Hello %s!\n", injectName);
	}
}
