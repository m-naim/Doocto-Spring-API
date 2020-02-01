package org.naim.doctoo.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Commune implements Serializable {
	@Column(length=45,nullable=false)
	private String name;
	
	@Id
	private String codeInsee;
	
	@ManyToOne(cascade = {CascadeType.PERSIST})
	private Departement departement;
	
	
	
	public Commune() {}
	
	public Commune(String name, String codeInsee) {
		this.name = name;
		this.codeInsee = codeInsee;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	

	
	public Departement getDepartement() {
		return departement;
	}
	public void setDepartement(Departement departement) {
		this.departement = departement;
	}
	@Override
	public String toString() {
		return "Commune [name=" + name + ", codeInsee=" + codeInsee +"]";
	}

	
	
	
}
