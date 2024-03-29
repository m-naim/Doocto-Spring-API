package org.naim.doctoo.payload;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import org.naim.doctoo.model.Location;
import org.naim.doctoo.mapper.DoctorInterface;
import org.naim.doctoo.mapper.UserInterface;
import org.naim.doctoo.model.Coordonnees;
import lombok.Data;

@Data
public class SignUpDoctorRequest implements UserInterface, DoctorInterface {
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
    private Location location;
    private String telephone;
    private Coordonnees coordonnees;
	
}
