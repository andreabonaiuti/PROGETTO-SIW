package it.uniroma3.controller;

import java.security.Principal;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import it.uniroma3.controller.validator.AllievoValidator;
import it.uniroma3.model.Allievo;
import it.uniroma3.service.AllievoService;
import it.uniroma3.service.AttivitaService;
import it.uniroma3.service.UserService;

@Controller
public class AllievoController {
	
	@Autowired
	private AllievoService allievoService;
	
	@Autowired
	private AttivitaService attivitaService;
	
	@Autowired
	private AllievoValidator validator;
	
	@Autowired
	private UserService userService;
	
	@RequestMapping("/registra")
	public String formAllievo(Model model) {
		model.addAttribute("allievo", new Allievo());
		return "allievoForm";
	}
	
	@PostMapping("/nuovoAllievo")
	public String inserisciAllievo(@Valid @ModelAttribute Allievo allievo, BindingResult result, Model model, Principal principal) {
		this.validator.validate(allievo, result);
		if(!result.hasErrors()) {
			allievo.setCentro(this.userService.findCurrentUser(principal).getCentro());
			this.allievoService.save(allievo);
			model.addAttribute("allievo", allievo);
			return "mostraAllievo";
		}
		else
			return "allievoForm";
	}
	
	@RequestMapping("/iscrivi")
	public String iscriviAllievo(Model model) {
		GregorianCalendar gc = (GregorianCalendar)Calendar.getInstance();
		model.addAttribute("allievi", this.allievoService.findAll());
		model.addAttribute("lista", this.attivitaService.findByData(gc));
		return "iscrizioneForm";
	}
	
	@RequestMapping("/allievo/{id}")
	public String mostraAllievo(@PathVariable("id") Long id, Model model) {
		model.addAttribute("allievo", this.allievoService.findById(id));
		return "mostraAllievo";
	}

}
