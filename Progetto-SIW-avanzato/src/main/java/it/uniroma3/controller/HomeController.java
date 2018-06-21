package it.uniroma3.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import it.uniroma3.service.DescrizioneService;
import it.uniroma3.model.DescrizioneAttivita;

@Controller
public class HomeController {
	
	@Autowired
	private DescrizioneService service;

	@RequestMapping("/home")
	public String vaiAllaHome() {
		return "home";
	}
}
