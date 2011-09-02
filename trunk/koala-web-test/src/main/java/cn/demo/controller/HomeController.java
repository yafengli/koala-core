package cn.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * User: Administrator
 * Date: 11-9-2
 * Time: 下午3:19
 */
@Controller
public class HomeController {
    @RequestMapping("/home")
    public String home() {
        return "WEB-INF/views/home.jsp";
    }

    @RequestMapping("/json")
    @ResponseBody
    public User json() {
        User user = new User();
        user.setName("Hello");
        user.setId(System.currentTimeMillis());
        return user;
    }
}

class User {
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
}
