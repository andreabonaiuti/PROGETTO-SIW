package it.uniroma3.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.model.Centro;
import it.uniroma3.model.Utente;

public interface UserRepository extends CrudRepository<Utente, Long> {

		public List<Utente> findAll();
		
		public Utente findByUsername(String username);

		public Utente findByCentro(Centro c);
}
