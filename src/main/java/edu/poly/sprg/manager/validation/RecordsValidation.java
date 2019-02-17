package edu.poly.sprg.manager.validation;

import edu.poly.sprg.manager.model.RecordsDTO;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class RecordsValidation implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return RecordsDTO.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

    }
}
