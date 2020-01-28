package org.naim.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class Docteur implements Serializable {
	@Id @GeneratedValue(strategy = GenerationType.SEQUENCE)
	private int id;
	
	private String civilite;
	
	@OneToOne(cascade = {CascadeType.PERSIST})
	private Commune commune;
			
	private String telephone;
	
	@Embedded
	private Coordonnees coordonnees;
	
	private String addresse;
	private String codePostal;
	
	@ManyToOne(cascade = {CascadeType.PERSIST})
	private Profession profession;
	private String nomProfessionel;
	
	
	
	public Docteur(String civilite, String telephone, String addresse,
			String codePostal, String nomProfessionel) {
		this.civilite = civilite;
		this.telephone = telephone;
		this.addresse = addresse;
		this.codePostal = codePostal;
		this.nomProfessionel = nomProfessionel;
	}
	
	

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

	public void setProfession(Profession profession) {
		this.profession = profession;
	}



	public Coordonnees getCoordonnees() {
		return coordonnees;
	}



	public void setCoordonnees(Coordonnees coordonnees) {
		this.coordonnees = coordonnees;
	}
	
	
	
}
