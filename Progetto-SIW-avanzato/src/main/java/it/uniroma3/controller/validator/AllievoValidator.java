package it.uniroma3.controller.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import it.uniroma3.model.Allievo;

@Component
public class AllievoValidator implements Validator {
	
	@Override
	public void validate(Object o, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nome", "label.mandatory");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "cognome", "label.mandatory");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "label.mandatory");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "telefono", "label.mandatory");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "luogoDiNascita", "label.mandatory");
	}
	
	@Override
	public boolean supports(Class<?> aClass) {
		return Allievo.class.equals(aClass);
	}

}
