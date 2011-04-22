package cn.demo.scan.controller;

import cn.demo.pojo.Student;
import cn.demo.support.WebBeanDefinition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.context.annotation.Scope;

/**
 * @author yafengli
 */
@Controller
@Scope(WebBeanDefinition.SCOPE_SESSION)
@RequestMapping({"/helloa.ftl"})
public class HelloWorldController {

    @Autowired
    private Validator validator;

    @InitBinder
    public void initHello(WebDataBinder binder) {
        try {
            System.out.printf("[%s,%s]", binder.getAllowedFields(),
                    binder.getObjectName());
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(method = {RequestMethod.POST})
    public String helloa(@ModelAttribute("test") Student student, BindingResult result, ModelMap model) {
//         new StudentValidator().validate(student, result);
        validator.validate(student, result);
        model.addAttribute("test", student);
        if (result.hasErrors())
            return "helloa";
        else
            return "helloah";
    }

    @RequestMapping(method = RequestMethod.GET)
    public void helloag(@RequestParam(required = false, value = "id") String id, ModelMap mmap) {
        mmap.addAttribute("test", new Student());
    }

    @ModelAttribute("message")
    public String getMessage() {
        return "Wo hello ModelAttribute!";
    }
}
