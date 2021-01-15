package org.naim.doctoo.model;

import java.io.Serializable;

import javax.persistence.Embeddable;

import lombok.Data;

@Data
@Embeddable
public class Coordonnees implements Serializable {
	private double lon;
	private double lat;

	public Coordonnees(double d, double e) {
		this.lon = d;
		this.lat = e;
	}

	public Coordonnees() {
	}

}
