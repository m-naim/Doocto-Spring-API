package org.naim.doctoo.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class Profession implements Serializable {

	@Id
	private String codeProfession;
	private String profession;

}
