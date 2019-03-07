package edu.poly.sprg.manager.validation;

import edu.poly.sprg.manager.model.StaffsDTO;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class StaffsValidation implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return StaffsDTO.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {

		StaffsDTO dto = (StaffsDTO) target;

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "validator.staff.name");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "birthDay", "validator.staff.birhday");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "phone", "validator.staff.phone");

		if (dto.getSalary() < 2500000) {
			errors.rejectValue("salary", "validator.staff.salary");
		}

		if(dto.getLevel() < 1 || dto.getLevel() > 10) {
			errors.rejectValue("level", "validator.staff.level");
		}
		
		if(!dto.getEmail().matches("\\b[\\w.%-]+@[-.\\w]+\\.[A-Za-z]{2,4}\\b")) {
			errors.rejectValue("email", "validator.staff.email");
		}
		
	}
}
