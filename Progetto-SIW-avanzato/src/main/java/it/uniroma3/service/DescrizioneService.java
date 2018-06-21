package it.uniroma3.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.repository.DescrizioneRepository;
import it.uniroma3.model.DescrizioneAttivita;

@Transactional
@Service
public class DescrizioneService {

	@Autowired
	private DescrizioneRepository repository;
	
	public List<DescrizioneAttivita> findAll() {
		return (List<DescrizioneAttivita>) this.repository.findAll();
	}

	public DescrizioneAttivita findById(Long id) {
		Optional<DescrizioneAttivita> desc = this.repository.findById(id);
		if(desc.isPresent()) {
			return desc.get();
		}
		else
			return null;
	}
	
	public void save(DescrizioneAttivita desc) {
		this.repository.save(desc);
	}
	
	public void clear() {
		this.repository.deleteAll();
	}

}
