package org.naim.doctoo.payload;

import java.util.Date;

import javax.validation.constraints.NotBlank;


public class AppointmentRequest {
	@NotBlank
	private Long docteurID;
	@NotBlank
	private Date date;
	
	public Long getDocteurID() {
		return docteurID;
	}
	public void setDocteurID(Long docteurID) {
		this.docteurID = docteurID;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	
	
	

}
