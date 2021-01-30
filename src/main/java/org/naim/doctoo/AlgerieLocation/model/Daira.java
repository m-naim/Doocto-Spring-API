package org.naim.doctoo.AlgerieLocation.model;
//avant cetait class Commune et table commune et redevient daira
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

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
@Table(name = "daira")
public class Daira implements Serializable{

//id n'est pas la cle primaire c'est le code_postal
	private String code;
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private int id;
	
	
	public Daira() {
		super();
	}

	public Daira(String code, int id, String name, String name_ar, Wilaya wilaya) {
		super();
		this.code = code;
		this.id = id;
		this.name = name;
		this.name_ar = name_ar;
		this.wilaya = wilaya;
	}

	private String name;
	private String name_ar;

	@ManyToOne
	@JoinColumn(name="wilaya_id")
	private Wilaya wilaya;
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public Wilaya getWilaya() {
		return wilaya;
	}

	public void setWilaya(Wilaya wilaya) {
		this.wilaya = wilaya;
	}

	@Override
	public String toString() {
		return "Daira [code=" + code + ", id=" + id + ", name=" + name + ", name_ar=" + name_ar + ", wilaya=" + wilaya
				+ "]";
	}
	


	

}
