package cn.demo.base.controller;

import cn.demo.pojo.Person;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.validation.BindException;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

import javax.servlet.http.HttpServletRequest;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class RegistroyStudentController extends SimpleFormController {
    @Override
    protected void initBinder(HttpServletRequest httpServletRequest, ServletRequestDataBinder servletRequestDataBinder) throws Exception {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        servletRequestDataBinder.registerCustomEditor(Date.class, new CustomDateEditor(df, false));
    }

    public RegistroyStudentController() {
        this.setCommandClass(Person.class);
    }

    @Override
    protected ModelAndView onSubmit(Object command, BindException errors)
            throws Exception {
        Person md = (Person) command;
        return new ModelAndView(getSuccessView(), "student", md);
    }
}
