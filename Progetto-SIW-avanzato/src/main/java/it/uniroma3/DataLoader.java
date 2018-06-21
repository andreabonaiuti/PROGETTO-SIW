package it.uniroma3;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import it.uniroma3.model.Allievo;
import it.uniroma3.model.Centro;
import it.uniroma3.repository.AllievoRepository;
import it.uniroma3.repository.CentroRepository;

@Component
public class DataLoader implements ApplicationRunner {
	
	@Autowired
	private AllievoRepository allievoRepository;
	
	@Autowired
	private CentroRepository centroRepository;

    @Autowired
    public DataLoader( AllievoRepository allievoRepository, CentroRepository centroRepository) {
        this.allievoRepository = allievoRepository;
        this.centroRepository = centroRepository;
    }

    public void run(ApplicationArguments args) {
    	this.allievoRepository.deleteAll();
    	this.centroRepository.deleteAll();
    	Centro c = new Centro("biblioteca", "ciao", "lo@ghd.it", 123, 456);
        this.centroRepository.save(c);
        Allievo a = new Allievo("Lorenzo", "Pannucci", "ciao@uniroma3.it", 321, "roma", c);
        this.allievoRepository.save(a);
    }
}