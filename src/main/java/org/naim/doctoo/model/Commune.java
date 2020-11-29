package org.naim.doctoo.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.Data;

@Data
@Entity
public class Commune implements Serializable {
	@Column(length = 45, nullable = false)
	private String name;

	@Id
	private String codeInsee;

	@ManyToOne(cascade = { CascadeType.PERSIST })
	private Departement departement;

}
