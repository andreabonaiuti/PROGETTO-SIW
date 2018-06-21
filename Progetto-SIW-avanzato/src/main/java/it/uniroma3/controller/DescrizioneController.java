package it.uniroma3.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import it.uniroma3.controller.validator.DescrizioneValidator;
import it.uniroma3.model.Attivita;
import it.uniroma3.model.DescrizioneAttivita;
import it.uniroma3.service.DescrizioneService;

@Controller
public class DescrizioneController {
	
	@Autowired
	private DescrizioneService service;
	
	@Autowired
	private DescrizioneValidator validator;
	
	@RequestMapping("/organizza")
	public String organizza(Model model) {
		model.addAttribute("descrizioneAttivita", new DescrizioneAttivita());
		model.addAttribute("descrizioni", this.service.findAll());
		return "mostraAttivita";
	}
	
	@PostMapping("/nuovaDescrizione")
	public String nuovaDescrizione(@Valid @ModelAttribute DescrizioneAttivita descrizioneAttivita,
			BindingResult result, Model model) {
		this.validator.validate(descrizioneAttivita, result);
		if(!result.hasErrors()) {
		this.service.save(descrizioneAttivita);
		Attivita attivita = new Attivita(descrizioneAttivita);
		model.addAttribute("attivita", attivita);
		return "attivitaForm";
		}
		else {
			return "attivitaForm";
		}
	}

}
