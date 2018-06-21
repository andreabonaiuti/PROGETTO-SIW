package it.uniroma3.model;

import java.util.GregorianCalendar;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Attivita {

	
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Long id;
	
	@OneToOne
	@NotNull
	private DescrizioneAttivita descrizione;
	
	@Column
	@NotNull
	@DateTimeFormat(pattern="dd-MM-yyyy")
	private GregorianCalendar data;
	
	@Column
	@NotNull
	@DateTimeFormat(pattern="hh-mm")
	private GregorianCalendar orario;
	
	@OneToMany
	private List<Allievo> allievi;
	
	@ManyToOne
	private Centro centro;
	
	public Attivita(DescrizioneAttivita desc) {
		this.descrizione = desc;
	}
	
	public GregorianCalendar getData() {
		return data;
	}
	public void setData(GregorianCalendar data) {
		this.data = data;
	}
	public GregorianCalendar getOrario() {
		return orario;
	}
	public void setOrario(GregorianCalendar orario) {
		this.orario = orario;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public DescrizioneAttivita getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(DescrizioneAttivita descrizione) {
		this.descrizione = descrizione;
	}

	public List<Allievo> getAllievi() {
		return allievi;
	}

	public void setAllievi(List<Allievo> allievi) {
		this.allievi = allievi;
	}

	public Centro getCentro() {
		return centro;
	}

	public void setCentro(Centro centro) {
		this.centro = centro;
	}

}
