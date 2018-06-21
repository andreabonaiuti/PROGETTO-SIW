package it.uniroma3.controller.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import it.uniroma3.model.Attivita;

@Component
public class AttivitaValidator implements Validator {
	
	@Override
	public void validate(Object att, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "data", "label.mandatory");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "orario", "label.mandatory");
	}
	
	@Override
	public boolean supports(Class<?> aClass) {
		return Attivita.class.equals(aClass);
	}

}
