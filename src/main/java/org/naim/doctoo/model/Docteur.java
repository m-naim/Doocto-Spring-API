package org.naim.doctoo.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import org.hibernate.validator.constraints.Currency;

import com.fasterxml.jackson.annotation.JsonMerge;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Entity
@Data
public class Docteur implements Serializable {
	@Id @GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;
	private String civilite;
	@OneToOne(cascade = {CascadeType.PERSIST})
	private Commune commune;
	private String telephone;
	@Embedded
	private Coordonnees coordonnees;
	private String addresse;
	@Column(name = "codePostal")
	private String codePostal;
	@ManyToOne(cascade = {CascadeType.ALL},fetch = FetchType.EAGER)
	private Profession profession;
	@Column(name = "nomProfessionel")
	private String nomProfessionel;
}
