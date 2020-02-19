package org.naim.doctoo.payload;

import java.util.Date;

import javax.validation.constraints.NotNull;


public class AppointmentRequest {
	@NotNull
	private Long docteurId;
	@NotNull
	private Date date;
	
	public Long getDocteurId() {
		return docteurId;
	}
	public void setDocteurId(Long docteurId) {
		this.docteurId = docteurId;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	
	
	

}
