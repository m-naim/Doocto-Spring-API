package org.naim.doctoo.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "profession")
public class Profession implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long codeProfession;
	@Column(unique = true)
	private String profession;

}
