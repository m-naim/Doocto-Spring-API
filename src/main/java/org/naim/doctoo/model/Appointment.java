package org.naim.doctoo.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(uniqueConstraints={@UniqueConstraint(columnNames = {"docteur_id", "date"})})
public class Appointment {
	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@ManyToOne
	private Docteur docteur;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
	private Date date;
	
	@ManyToOne
	private User user;
	
	public Appointment() {	}
	
	

	public Appointment(Docteur docteur, Date date, User user) {
		this.docteur = docteur;
		this.date = date;
		this.user = user;
	}



	public Docteur getDocteur() {
		return docteur;
	}

	public void setDocteur(Docteur docteur) {
		this.docteur = docteur;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}



	public long getId() {
		return id;
	}



	public void setId(long id) {
		this.id = id;
	}

	
	
}
