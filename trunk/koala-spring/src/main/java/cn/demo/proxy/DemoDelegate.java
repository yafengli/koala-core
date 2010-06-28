package cn.demo.proxy;

import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DemoDelegate {
	
	public ModelAndView sayHello(HttpServletRequest req,HttpServletResponse resp)
	{
		return new ModelAndView("hello","action","Say hello.");
	}
	public ModelAndView logIn(HttpServletRequest req,HttpServletResponse resp)
	{
		return new ModelAndView("hello","action","Login");
	}
	public ModelAndView logOut(HttpServletRequest req,HttpServletResponse resp)
	{
		return new ModelAndView("hello","action","Logout");
	}
}
