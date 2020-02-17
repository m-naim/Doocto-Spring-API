package org.naim.doctoo.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.ManyToMany;

@Entity
@IdClass(AppointmentCompositeKey.class)
public class Appointment {
	@Id
	@ManyToMany
	Docteur docteur;
	@Id
	Date date;
	
	@ManyToMany
	User user;
	
	public Appointment() {	}

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

	
}
