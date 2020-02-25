package org.naim.doctoo.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import org.hibernate.validator.constraints.Currency;

import com.fasterxml.jackson.annotation.JsonMerge;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
public class Docteur implements Serializable {
	@Id @GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;
	
	private String civilite;
	
	@OneToOne(cascade = {CascadeType.PERSIST})
	private Commune commune;
			
	private String telephone;
	
	@Embedded
	private Coordonnees coordonnees;
	
	private String addresse;
	@Column(name = "codePostal")
	private String codePostal;
	
	@ManyToOne(cascade = {CascadeType.ALL},fetch = FetchType.EAGER)
	private Profession profession;
	
	@Column(name = "nomProfessionel")
	private String nomProfessionel;
	
	
	public Docteur() {	}



	public String getNomProfessionel() {
		return nomProfessionel;
	}

	public void setNomProfessionel(String nomProfessionel) {
		this.nomProfessionel = nomProfessionel;
	}

	public String getAddresse() {
		return addresse;
	}

	public void setAddresse(String addresse) {
		this.addresse = addresse;
	}


	public String getCivilite() {
		return civilite;
	}

	public void setCivilite(String civilite) {
		this.civilite = civilite;
	}

	public Commune getCommune() {
		return commune;
	}

	public void setCommune(Commune commune) {
		this.commune = commune;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getCodePostal() {
		return codePostal;
	}

	public void setCodePostal(String codePostal) {
		this.codePostal = codePostal;
	}


	public Profession getProfession() {
		return profession;
	}
	
	public String getProfessionName() {
		return profession.getProfession();
	}

	public void setProfession(Profession profession) {
		this.profession = profession;
	}
	
	public String getNameCommune() {
		return this.commune.getName();
	}




	public Coordonnees getCoordonnees() {
		return coordonnees;
	}



	public void setCoordonnees(Coordonnees coordonnees) {
		this.coordonnees = coordonnees;
	}



	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}
	
	
	
}
