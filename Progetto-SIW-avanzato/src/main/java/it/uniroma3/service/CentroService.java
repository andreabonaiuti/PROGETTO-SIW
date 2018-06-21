package it.uniroma3.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.model.Centro;
import it.uniroma3.repository.CentroRepository;

@Service
public class CentroService {
	
	@Autowired
	private CentroRepository repository;

	public List<Centro> findAll() {
		return this.repository.findAll();
	}

	public Centro findById(Long id) {
		Optional<Centro> opt = this.repository.findById(id);
		if(opt.isPresent()) {
			return opt.get();
		}
		else
			return null;
	}

}
