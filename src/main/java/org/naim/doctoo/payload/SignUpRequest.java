package org.naim.doctoo.payload;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import org.naim.doctoo.mapper.UserInterface;

import lombok.Data;

@Data
public class SignUpRequest implements UserInterface {
    @NotBlank
    private String name;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    private String password;


}