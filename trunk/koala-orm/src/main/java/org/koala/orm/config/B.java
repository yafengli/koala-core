package org.koala.orm.config;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

/**
 * User: phoenixup
 * Date: 11-3-15
 * Time: 下午12:29
 * Desc: //TODO:WRITE YOUR OWN DESCRIPTION.
 */
@Component("b")
public class B {
	@Resource(name = "a")
	private A a;

	public A getA() {
		return a;
	}

	public void setA(A a) {
		this.a = a;
	}

	@PostConstruct
	public void initB() {
		System.out.println("B init do.");
	}
}
