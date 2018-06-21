package it.uniroma3.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.model.DescrizioneAttivita;

public interface DescrizioneRepository extends CrudRepository<DescrizioneAttivita, Long> {

	public List<DescrizioneAttivita> findAll();
}
