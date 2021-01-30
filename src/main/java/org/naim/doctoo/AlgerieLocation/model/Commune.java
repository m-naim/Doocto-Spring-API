package org.naim.doctoo.AlgerieLocation.model;
//avant cetait class Projet et table Projet et redevient commune 

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Commune")
public class Commune implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String code;
	private String name;
	private String name_ar;
	


	@ManyToOne( )
	@JoinColumn(name = "daira_id", referencedColumnName = "id")
	// @JoinColumn(name="code_postal" )
	private Daira daira;

	

	
	public Commune() {
		super();
	}


	public Commune(int id, String code, String name, String name_ar, Daira daira) {
		super();
		this.id = id;
		this.code = code;
		this.name = name;
		this.name_ar = name_ar;
		this.daira = daira;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName_ar() {
		return name_ar;
	}

	public void setName_ar(String name_ar) {
		this.name_ar = name_ar;
	}

	public Daira getDaira() {
		return daira;
	}

	public void setDaira(Daira daira) {
		this.daira = daira;
	}

	@Override
	public String toString() {
		return "Commune [id=" + id + ", code=" + code + ", name=" + name + ", name_ar=" + name_ar + ", daira=" + daira
				+ "]";
	}


	



}
