package org.naim.doctoo.model;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.Id;

import lombok.Data;

@Data
@Embeddable
public class Coordonnees implements Serializable {
	private String lon;
	private String lat;
	
	public Coordonnees(String lon, String lat) {
		this.lon = lon;
		this.lat = lat;
	}

	public Coordonnees() {
	}
	
}
