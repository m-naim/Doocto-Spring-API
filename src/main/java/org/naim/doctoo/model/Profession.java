package org.naim.doctoo.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class Profession implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long codeProfession;
	private String profession;

}
