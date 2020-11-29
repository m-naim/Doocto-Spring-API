package org.naim.doctoo.controller;
import java.net.URI;

import javax.validation.Valid;

import org.naim.doctoo.exception.BadRequestException;
import org.naim.doctoo.model.AuthProvider;
import org.naim.doctoo.model.Docteur;
import org.naim.doctoo.model.User;
import org.naim.doctoo.payload.ApiResponse;
import org.naim.doctoo.payload.AuthResponse;
import org.naim.doctoo.payload.LoginRequest;
import org.naim.doctoo.payload.SignUpRequest;
import org.naim.doctoo.payload.SignUpDoctorRequest;
import org.naim.doctoo.repository.DocteurRepository;
import org.naim.doctoo.repository.UserRepository;
import org.naim.doctoo.security.TokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private TokenProvider tokenProvider;
    @Autowired
	private DocteurRepository docteurRepository;

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getEmail(),
                        loginRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = tokenProvider.createToken(authentication);
        return ResponseEntity.ok(new AuthResponse(token));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpRequest signUpRequest) {
        if(userRepository.existsByEmail(signUpRequest.getEmail())) {
            throw new BadRequestException("Email address already in use.");
        }

        // Creating user's account
        User user = new User();
        user.setName(signUpRequest.getName());
        user.setEmail(signUpRequest.getEmail());
        user.setPassword(signUpRequest.getPassword());
        user.setProvider(AuthProvider.local);

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        User result = userRepository.save(user);

        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath().path("/user/me")
                .buildAndExpand(result.getId()).toUri();

        return ResponseEntity.created(location)
                .body(new ApiResponse(true, "User registered successfully@"));
    }

    @PostMapping("/signup/doctor")
    public ResponseEntity<?> registerDocteur(@Valid @RequestBody SignUpDoctorRequest signUpDoctorRequest) {
        if(userRepository.existsByEmail(signUpDoctorRequest.getEmail())) {
            throw new BadRequestException("Email address already in use.");
        }

        // Creating user's account
        User user = new User();
        user.setName(signUpDoctorRequest.getName());
        user.setEmail(signUpDoctorRequest.getEmail());
        user.setPassword(signUpDoctorRequest.getPassword());
        user.setProvider(AuthProvider.local);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        
        Docteur docteur= new Docteur();
        docteur.setNomProfessionel(signUpDoctorRequest.getName());
        docteur.setAddresse(signUpDoctorRequest.getAdresse());
        docteur.setCivilite(signUpDoctorRequest.getCivilite());
        docteur.setCodePostal(signUpDoctorRequest.getCodePostal());
        docteur.setCommune(signUpDoctorRequest.getCommune());
        docteur.setProfession(signUpDoctorRequest.getProfession());
        docteur.setTelephone(signUpDoctorRequest.getTelephone());
        docteur.setCoordonnees(signUpDoctorRequest.getCoordonnees());
        docteur= docteurRepository.save(docteur); 
        
        user.setDocteur(docteur);
        User result = userRepository.save(user);

        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath().path("/user/me")
                .buildAndExpand(result.getId()).toUri();

        return ResponseEntity.created(location)
                .body(new ApiResponse(true, "User registered successfully@"));
    }
}