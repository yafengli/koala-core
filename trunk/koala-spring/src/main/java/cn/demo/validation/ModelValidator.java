package cn.demo.validation;

import cn.demo.pojo.Person;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class ModelValidator implements Validator {

    public boolean supports(Class arg0) {
        return arg0.equals(Person.class);
    }

    public void validate(Object object, Errors errors) {
        Person student = (Person) object;
        if (student.getSex() == null || student.getSex().equals("")) {
            errors.rejectValue("sex", "error.sex", null, "sex is required.");
        }
        if (student.getName() == null || student.getName().trim().length() > 10 || student.getName().trim().length() < 5) {
            errors.rejectValue("name", "error.name", null, "username is required,the length is 5 to 10.");
        }
        if (student.getAge() == null || student.getAge().intValue() < 1 || student.getAge().intValue() > 100) {
            errors.rejectValue("age", "error.age");
        }
    }
}
