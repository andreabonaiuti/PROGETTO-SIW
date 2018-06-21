package it.uniroma3.service;

import java.util.GregorianCalendar;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.model.Allievo;
import it.uniroma3.model.Attivita;
import it.uniroma3.model.Centro;
import it.uniroma3.repository.AttivitaRepository;

@Transactional
@Service
public class AttivitaService {
	
	@Autowired
	private AttivitaRepository repository;
	
	public List<Attivita> findAll() {
		return (List<Attivita>) this.repository.findAll();
	}
	
	public void save(Attivita attivita) {
		this.repository.save(attivita);
	}
	
	public List<Attivita> findByData(GregorianCalendar data) {
		return this.repository.findByData(data);
	}

	public Attivita findById(Long idAttivita) {
		Optional<Attivita> opt = this.repository.findById(idAttivita);
		if(opt.isPresent()) {
			return opt.get();
		}
		else
			return null;
	}

	public void iscriviAllievo(Attivita attivita, Allievo allievo) {
		attivita.getAllievi().add(allievo);
		this.repository.save(attivita);
	}

	public List<Attivita> findByCentro(Centro c) {
		return this.repository.findByCentro(c);
	}

}
