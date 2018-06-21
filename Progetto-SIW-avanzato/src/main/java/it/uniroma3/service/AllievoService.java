package it.uniroma3.service;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.uniroma3.model.Allievo;
import it.uniroma3.model.Centro;
import it.uniroma3.repository.AllievoRepository;

@Transactional
@Service
public class AllievoService {
	
	@Autowired
	private AllievoRepository repository;

	public void save(@Valid Allievo allievo) {
		this.repository.save(allievo);
	}
	
	public List<Allievo> findAll() {
		return (List<Allievo>)this.repository.findAll();
	}

	public Allievo findById(Long idAllievo) {
		Optional<Allievo> opt = this.repository.findById(idAllievo);
		if(opt.isPresent()) {
			return opt.get();
		}
		else
			return null;
	}

	public List<Allievo> findByCentro(Centro c) {
		return this.repository.findByCentro(c);
	}

}
