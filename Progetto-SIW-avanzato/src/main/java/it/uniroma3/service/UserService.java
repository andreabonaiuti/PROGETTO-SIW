package it.uniroma3.service;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import it.uniroma3.model.Centro;
import it.uniroma3.model.Utente;
import it.uniroma3.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
    private UserRepository userRepository; 

    public List<Utente> findAll() {
        return this.userRepository.findAll();
    }

    @Transactional
    public void add(final Utente user) {
        this.userRepository.save(user);
    }

	public Utente findbyId(Long id) {
		Optional<Utente> o = this.userRepository.findById(id);
		if(o.isPresent()) {
			return o.get();
		}
		else 
			return null;
	}
	
	public Utente findCurrentUser(Principal principal) {
		String username = principal.getName();
		return this.userRepository.findByUsername(username);
	}

	public Utente findByCentro(Centro c) {
		return this.userRepository.findByCentro(c);
	}

}
