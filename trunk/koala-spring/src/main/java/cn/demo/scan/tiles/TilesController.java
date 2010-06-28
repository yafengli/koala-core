package cn.demo.scan.tiles;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author YaFengLi
 */
@Controller
@RequestMapping("/")
public class TilesController {

    @RequestMapping("home/{id}/")
    public ModelAndView forward(@PathVariable String id, Model model) {
        ModelAndView mav = new ModelAndView("home");
        System.out.println("homepage");
        model.addAttribute("message", "我去，这是个测试页面！");
        return mav;
    }
}
