package org.naim.doctoo.AlgerieLocation.model;
//avant c'etait class Departement et table departement et redevient wilaya 
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.context.annotation.Lazy;

@Entity
@Table(name = "wilaya")
public class Wilaya implements Serializable{
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	private String name;
	private String name_ar;
	private String code;
	
	
	public Wilaya(int id, String name, String name_ar, String code) {
		super();
		this.id = id;
		this.name = name;
		this.name_ar = name_ar;
		this.code = code;
	}
	public Wilaya() {
		super();
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
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	@Override
	public String toString() {
		return "Wilaya [id=" + id + ", name=" + name + ", name_ar=" + name_ar + ", code=" + code + "]";
	}
	



}
