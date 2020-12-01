package org.naim.doctoo.controller;

import java.util.Optional;

import org.naim.doctoo.exception.ResourceNotFoundException;
import org.naim.doctoo.model.Docteur;
import org.naim.doctoo.model.Profession;
import org.naim.doctoo.model.User;
import org.naim.doctoo.payload.ApiResponse;
import org.naim.doctoo.payload.SignUpDoctorRequest;
import org.naim.doctoo.repository.DocteurRepository;
import org.naim.doctoo.repository.ProfessionRepository;
import org.naim.doctoo.repository.UserRepository;
import org.naim.doctoo.security.CurrentUser;
import org.naim.doctoo.security.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DoctorController {
	@Autowired
	private DocteurRepository docteurRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	ProfessionRepository professionRepository;
	
	@GetMapping("/doc/me")
    @PreAuthorize("hasRole('USER')")
	public Docteur getdocInfos(@CurrentUser UserPrincipal userPrincipal) {
		Optional<User> user= userRepository.findById(userPrincipal.getId());
		return docteurRepository.findById(user.get().getDocteur().getId())
				.orElseThrow(() -> new ResourceNotFoundException("Appointments of User", "id", userPrincipal.getId()));
	}
	
	@PostMapping("/doc/infos")
	@PreAuthorize("hasRole('USER')")
	public ApiResponse editDocInfos(@RequestBody SignUpDoctorRequest signUpDoctorRequest) {
		Optional<User> user = userRepository.findByEmail(signUpDoctorRequest.getEmail());
		if(!user.isPresent()) throw new ResourceNotFoundException("doctor", "email", signUpDoctorRequest.getEmail());
		
		Docteur docteur = user.get().getDocteur();
        docteur.setAddresse(signUpDoctorRequest.getAdresse());
        docteur.setCivilite(signUpDoctorRequest.getCivilite());
        docteur.setCodePostal(signUpDoctorRequest.getCodePostal());
        docteur.setCommune(signUpDoctorRequest.getCommune());
        docteur.setNomProfessionel(signUpDoctorRequest.getName());
        
        Optional<Profession> profession = professionRepository.findByProfession(signUpDoctorRequest.getProfession());
        if(profession.isPresent())
        	docteur.setProfession(profession.get());
        else{
        	Profession newProfession= new Profession();
        	newProfession.setProfession(signUpDoctorRequest.getProfession());
        	newProfession=professionRepository.save(newProfession);
        	docteur.setProfession(newProfession);
        }

        docteur.setTelephone(signUpDoctorRequest.getTelephone());
        docteur.setCoordonnees(signUpDoctorRequest.getCoordonnees());
        docteur= docteurRepository.save(docteur); 
        
        return new ApiResponse(true, "doctors infos updated with success");
	}
	
	public void setDocAvaibilites() {
	}
	
}
