package cn.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.demo.bean.User;

/**
 * User: Administrator Date: 11-9-2 Time: 下午3:19
 */
@Controller
public class HomeController {
	@RequestMapping("/home")
	public String home() {
		return "WEB-INF/views/home.jsp";
	}

	@RequestMapping("/view")
	public String view() {
		return "WEB-INF/views/view.jsp";
	}
	@RequestMapping("/json")
	@ResponseBody
	public User json() {
		User user = new User();
		user.setName("Hello");
		user.setId(System.currentTimeMillis());
		return user;
	}

	@RequestMapping("/list")
	@ResponseBody
	public List<Integer> list() {
		List<Integer> list = new ArrayList<Integer>();
		for (int i = 0; i < 10; i++) {
			list.add((int) (Math.random() * 500+500));
		}
		return list;
	}
}
