package cn.demo.validation;

import cn.demo.pojo.Student;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: 2008-12-11
 * Time: 14:58:42
 * To change this template use File | Settings | File Templates.
 */

public class StudentValidator implements Validator {
    public boolean supports(Class aClass) {
        return aClass.equals(Student.class);
    }

    public void validate(Object o, Errors errors) {
        if (o != null && o instanceof Student) {
            Student test = (Student) o;

            System.out.println("|" + test.getFirstName() + "|" + test.getLastName() + "|");

            if (test.getFirstName() == null || test.getFirstName().trim().length() < 1) {
                errors.rejectValue("firstName", "required", new Object[]{"FirstName"}, "required");
            }
            if (test.getLastName() == null || test.getLastName().trim().length() < 1) {
                errors.rejectValue("lastName", "required", "The content is required.");
            }
        }
        else
           errors.rejectValue("noin","noin.error");
    }
}
