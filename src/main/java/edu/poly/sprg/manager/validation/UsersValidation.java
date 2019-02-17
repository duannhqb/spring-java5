package edu.poly.sprg.manager.validation;

import edu.poly.sprg.manager.model.UsersDTO;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class UsersValidation implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return UsersDTO.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

    }
}
