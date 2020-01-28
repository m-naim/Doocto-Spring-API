package org.naim.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Profession implements Serializable {
	@Id
	private String codeProfession;
	
	private String profession;
	
	

	public Profession() {	}

	public Profession(String codeProfession, String profession) {
		this.codeProfession = codeProfession;
		this.profession = profession;
	}

	public String getCodeProfession() {
		return codeProfession;
	}

	public void setCodeProfession(String codeProfession) {
		this.codeProfession = codeProfession;
	}

	public String getProfession() {
		return profession;
	}

	public void setProfession(String profession) {
		this.profession = profession;
	}
	
	
}
