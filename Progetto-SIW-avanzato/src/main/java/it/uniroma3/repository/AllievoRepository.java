package it.uniroma3.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.model.Allievo;
import it.uniroma3.model.Centro;

public interface AllievoRepository extends CrudRepository<Allievo, Long> {
	
	public List<Allievo> findByCentro(Centro c);

}
