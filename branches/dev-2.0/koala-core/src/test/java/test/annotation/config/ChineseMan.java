package test.annotation.config;

import org.springframework.stereotype.Service;

@Service("chineseman")
public class ChineseMan implements IMan {

	public void sayHello() {
		System.out.println("万里长城今犹在！");
	}
}
