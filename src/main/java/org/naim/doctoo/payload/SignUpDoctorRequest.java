package org.naim.doctoo.payload;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import org.naim.doctoo.model.Commune;
import org.naim.doctoo.model.Coordonnees;
import org.naim.doctoo.model.Profession;

import lombok.Data;

@Data
public class SignUpDoctorRequest {
    @NotBlank
    private String name;

    @NotBlank
    @Email
    private String email;
    @NotBlank
    private String password;
    private String adresse;
    private String civilite;
    private String profession;
    private Commune commune;
    private String codePostal;
    private String telephone;
    private Coordonnees coordonnees;
	
}
