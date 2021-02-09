package org.naim.doctoo.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Entity
@Data
@Table(name = "doctor")
public class Doctor implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String civilite;
	
	
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "location_id", nullable = false)
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	private Location location;
	
	private String telephone;
	@Embedded
	private Coordonnees coordonnees;
	private String addresse;
	@Column(name = "codePostal")
	private String codePostal;
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "profession_id", nullable = false)
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	private Profession profession;
	
	@Column(name = "nomProfessionel")
	private String nomProfessionel;
	
	@Column(name = "schedule")
	private String schedule;
	
	@Column(name = "shiftDuration")
	private String shiftDuration;
	
	@Column(name = "presentation")
	private String presentation;
	
	@Column(name = "accessIndecations")
	private String accessIndecations;
	
	
}
