package it.uniroma3.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import it.uniroma3.model.Centro;
import it.uniroma3.service.AllievoService;
import it.uniroma3.service.AttivitaService;
import it.uniroma3.service.CentroService;
import it.uniroma3.service.UserService;

@Controller
public class CentroController {
	
	@Autowired
	private CentroService centroService;
	
	@Autowired
	private AllievoService allievoService;
	
	@Autowired
	private AttivitaService attivitaService;
	
	@Autowired
	private UserService userService;
	
	@RequestMapping("/mostraCentri")
	public String mostraCentri(Model model) {
		model.addAttribute("centri", this.centroService.findAll());
		return "listaCentri";
	}
	
	@RequestMapping("/centro/{id}")
	public String accediCentro(@PathVariable("id") Long id, Model model) {
		Centro c = this.centroService.findById(id);
		model.addAttribute("centro", c);
		return "centro";
	}
	
	@RequestMapping("/centro/{id}/allievi")
	public String elencoAllievi(@PathVariable("id") Long id, Model model) {
		Centro c = this.centroService.findById(id);
		model.addAttribute("allievi", this.allievoService.findByCentro(c));
		return "elencoAllievi";
	}
	
	@RequestMapping("/centro/{id}/attivita")
	public String elencoAttivita(@PathVariable("id") Long id, Model model) {
		Centro c = this.centroService.findById(id);
		model.addAttribute("elenco", this.attivitaService.findByCentro(c));
		return "elencoAttivita";
	}
	
	@RequestMapping("/centro/{id}/responsabili")
	public String elencoResponsabili(@PathVariable("id") Long id, Model model) {
		Centro c = this.centroService.findById(id);
		model.addAttribute("responsabili", this.userService.findByCentro(c));
		return "elencoResponsabili";
	}
	
	@RequestMapping("/visualizza")
	public String visualizza(Model model, Principal principal) {
		model.addAttribute("centro", this.userService.findCurrentUser(principal).getCentro());
		return "centro";
	}

}
