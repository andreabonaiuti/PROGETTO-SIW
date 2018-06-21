package it.uniroma3.controller.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import it.uniroma3.model.DescrizioneAttivita;

@Component
public class DescrizioneValidator implements Validator{
	
	@Override
	public void validate(Object desc, Errors errors) {
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nome", "label.mandatory");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "descrizione", "label.mandatory");
	}
	
	@Override
	public boolean supports(Class<?> aClass) {
		return DescrizioneAttivita.class.equals(aClass);
	}

}
