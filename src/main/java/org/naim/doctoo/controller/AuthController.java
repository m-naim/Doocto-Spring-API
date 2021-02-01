package org.naim.doctoo.controller;
import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.naim.doctoo.exception.BadRequestException;
import org.naim.doctoo.mapper.DoctorMapper;
import org.naim.doctoo.mapper.UserMapper;
import org.naim.doctoo.model.AuthProvider;
import org.naim.doctoo.model.Doctor;
import org.naim.doctoo.model.DoctorInscription;
import org.naim.doctoo.model.Location;
import org.naim.doctoo.model.Profession;
import org.naim.doctoo.model.User;
import org.naim.doctoo.payload.ApiResponse;
import org.naim.doctoo.payload.AuthResponse;
import org.naim.doctoo.payload.LoginRequest;
import org.naim.doctoo.payload.SignUpRequest;
import org.naim.doctoo.payload.SignUpDoctorRequest;
import org.naim.doctoo.repository.DocteurInscriptionRepository;
import org.naim.doctoo.repository.DocteurRepository;
import org.naim.doctoo.repository.LocationRepository;
import org.naim.doctoo.repository.ProfessionRepository;
import org.naim.doctoo.repository.UserRepository;
import org.naim.doctoo.security.TokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.BodyBuilder;
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
    @Autowired
    private DocteurInscriptionRepository docteurInscriptionRepository;
    @Autowired
    ProfessionRepository professionRepository;
    @Autowired
	private LocationRepository locationRepository;

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
        User user = UserMapper.mapObject(signUpRequest);
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
        User user = UserMapper.mapObject(signUpDoctorRequest);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        
        Doctor doctor= DoctorMapper.mapObject(signUpDoctorRequest);
        
        String daira = signUpDoctorRequest.getLocation().getDaira();
		Optional<Location> optionalLocation = locationRepository.findByDaira(daira);
        
        if (!optionalLocation.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }
        doctor.setLocation(optionalLocation.get());

        
        Optional<Profession> profession = professionRepository.findByProfession(signUpDoctorRequest.getProfession());
        if(profession.isPresent())
        	doctor.setProfession(profession.get());
        else{
        	Profession newProfession= new Profession();
        	newProfession.setProfession(signUpDoctorRequest.getProfession());
        	newProfession=professionRepository.save(newProfession);
        	doctor.setProfession(newProfession);
        }
        
        doctor= docteurRepository.save(doctor); 
        user.setDoctor(doctor);
        User result = userRepository.save(user);

        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath().path("/user/me")
                .buildAndExpand(result.getId()).toUri();

        return ResponseEntity.created(location)
                .body(new ApiResponse(true, "User registered successfully@"));
    }
    
    /************************************************************************/
    @PostMapping("/signup/doctorsInscription")
    public ResponseEntity<?> registerDocteurInscription(@Valid @RequestBody SignUpDoctorRequest signUpDoctorRequest) {
        if(userRepository.existsByEmail(signUpDoctorRequest.getEmail())) {
            throw new BadRequestException("Email address already in use.");
        }
        
        DoctorInscription doc = DoctorMapper.mapObjectInscription(signUpDoctorRequest);
        
        String daira = signUpDoctorRequest.getLocation().getDaira();
		Optional<Location> optionalLocation = locationRepository.findByDaira(daira);
        
        if (!optionalLocation.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }
        doc.setLocation(optionalLocation.get());
        docteurInscriptionRepository.save(doc); 
        

		return ((BodyBuilder) ResponseEntity.ok()).body(new ApiResponse(true, "DoctorInscription registered successfully@"));
    }
    /**************************************************************************************/
    
}