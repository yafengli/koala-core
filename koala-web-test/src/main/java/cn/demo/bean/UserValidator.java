package cn.demo.bean;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class UserValidator implements Validator {

	@Override
	public boolean supports(Class<?> c) {
		return User.class.equals(c);
	}

	@Override
	public void validate(Object obj, Errors errors) {
		if (obj instanceof User) {
			User user = (User) obj;
			if (user.getId() == null || user.getId() > 99) {
				errors.reject("id", "TOO MAX.");
			}
			if (user.getAddress() == null || user.getAddress().length() > 4) {
				errors.reject("id", "TOO LONG.");
			}
		}
	}
}
