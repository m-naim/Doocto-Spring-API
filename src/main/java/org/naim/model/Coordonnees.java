package org.naim.model;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.Id;

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


	public String getLon() {
		return lon;
	}
	public void setLon(String lon) {
		this.lon = lon;
	}
	public String getLat() {
		return lat;
	}
	public void setLat(String lat) {
		this.lat = lat;
	}
	
}
