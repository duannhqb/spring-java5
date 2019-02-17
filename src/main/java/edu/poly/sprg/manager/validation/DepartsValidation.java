package edu.poly.sprg.manager.validation;

import edu.poly.sprg.manager.model.DepartsDTO;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class DepartsValidation implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return DepartsDTO.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

    }
}
