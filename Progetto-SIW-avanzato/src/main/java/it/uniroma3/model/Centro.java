package it.uniroma3.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

@Entity
public class Centro {

	@Id
	@GeneratedValue (strategy = GenerationType.AUTO)
	private Long id;
	
	@Column
	@NotNull
	private String nome;
	
	@Column
	@NotNull
	private String indirizzo;
	
	@Column
	@NotNull
	private String email;
	
	@Column
	@NotNull
	private int telefono;
	
	@Column
	@NotNull
	private int capienza;
	
	@OneToMany
	private List<Attivita> attivita;
	
	@OneToMany
	private List<Allievo> allievo;
	
	public Centro(String nome, String indirizzo, String email, int telefono, int capienza) {
		this.nome = nome;
		this.indirizzo = indirizzo;
		this.email = email;
		this.telefono = telefono;
		this.capienza = capienza;
	}
	
	public Centro() {
		
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getIndirizzo() {
		return indirizzo;
	}
	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getTelefono() {
		return telefono;
	}
	public void setTelefono(int telefono) {
		this.telefono = telefono;
	}
	public int getCapienza() {
		return capienza;
	}
	public void setCapienza(int capienza) {
		this.capienza = capienza;
	}
}
