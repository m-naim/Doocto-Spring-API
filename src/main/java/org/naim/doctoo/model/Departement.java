package org.naim.doctoo.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.Data;

@Entity
@Data
public class Departement implements Serializable {
	@Id
	private String id;
	@Column(length=25,nullable=false)
	private String name;
	@OneToMany(mappedBy = "departement")
	private List<Commune> communes = new ArrayList<Commune>();
	
}
