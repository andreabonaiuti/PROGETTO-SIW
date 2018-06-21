package it.uniroma3.repository;

import java.util.GregorianCalendar;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.model.Attivita;
import it.uniroma3.model.Centro;

public interface AttivitaRepository extends CrudRepository<Attivita, Long> {
	
	public List<Attivita> findByData(GregorianCalendar data);

	public List<Attivita> findByCentro(Centro c);

}
