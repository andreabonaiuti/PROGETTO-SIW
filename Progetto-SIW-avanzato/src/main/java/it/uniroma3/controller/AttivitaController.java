package it.uniroma3.controller;

import java.security.Principal;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import it.uniroma3.controller.validator.AttivitaValidator;
import it.uniroma3.model.Allievo;
import it.uniroma3.model.Attivita;
import it.uniroma3.model.DescrizioneAttivita;
import it.uniroma3.service.AllievoService;
import it.uniroma3.service.AttivitaService;
import it.uniroma3.service.DescrizioneService;
import it.uniroma3.service.UserService;

@Controller
public class AttivitaController {
	
	@Autowired
	private AttivitaService attivitaService;
	
	@Autowired
	private DescrizioneService descrizioneService;
	
	@Autowired
	private AttivitaValidator validator;

	@Autowired
	private AllievoService allievoService;
	
	@Autowired
	private UserService userService;
	
	@RequestMapping("/crea/{id}")
	public String creaAttivita(@PathVariable("id") Long id, Model model) {
		DescrizioneAttivita desc = this.descrizioneService.findById(id);
		Attivita attivita = new Attivita(desc);
		model.addAttribute("attivita", attivita);
		return "attivitaForm";
	}
	
	@PostMapping("/inserisci")
	public String inserisciAttivita(@Valid @ModelAttribute Attivita attivita, BindingResult result, Model model, Principal principal) {
		this.validator.validate(attivita, result);
		if(!result.hasErrors()) {
			attivita.setCentro(this.userService.findCurrentUser(principal).getCentro());
			this.attivitaService.save(attivita);
			model.addAttribute("elenco", this.attivitaService.findByData(attivita.getData()));
			return "giornata";
		}
		else {
			return "attivitaForm";
		}
	}
	
	@RequestMapping("/salvaIscrizione")
	public String salvaIscrizione(Model model, @RequestParam(value="allievo") Long idAllievo,
			@RequestParam(value="attivita") Long idAttivita, BindingResult result) {
		Allievo allievo = this.allievoService.findById(idAllievo);
		Attivita attivita = this.attivitaService.findById(idAttivita);
		this.attivitaService.iscriviAllievo(attivita, allievo);
		return "home";
	}
	
	@RequestMapping("attivita/{id}")
	public String mostraAttivita(@PathVariable("id") Long id, Model model) {
		model.addAttribute("attivita", this.attivitaService.findById(id));
		return "attivita";
	}

}
