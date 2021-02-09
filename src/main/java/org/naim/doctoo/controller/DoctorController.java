package org.naim.doctoo.controller;

import java.util.Optional;

import org.naim.doctoo.exception.ResourceNotFoundException;
import org.naim.doctoo.model.Doctor;
import org.naim.doctoo.model.Schedule;
import org.naim.doctoo.model.User;
import org.naim.doctoo.payload.ApiResponse;
import org.naim.doctoo.payload.DoctorUpdateRequest;
import org.naim.doctoo.repository.DocteurRepository;
import org.naim.doctoo.repository.ProfessionRepository;
import org.naim.doctoo.repository.UserRepository;
import org.naim.doctoo.repository.projection.ScheduleView;
import org.naim.doctoo.security.CurrentUser;
import org.naim.doctoo.security.UserPrincipal;
import org.naim.doctoo.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javassist.NotFoundException;

@RestController
public class DoctorController {
	@Autowired
	private DocteurRepository docteurRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	ProfessionRepository professionRepository;
	@Autowired
	private DoctorService doctorService;
	
	@GetMapping("/doc/me")
    @PreAuthorize("hasRole('USER')")
	public Doctor getdocInfos(@CurrentUser UserPrincipal userPrincipal) {
		Optional<User> user= userRepository.findById(userPrincipal.getId());
		return docteurRepository.findById(user.get().getDoctor().getId())
				.orElseThrow(() -> new ResourceNotFoundException("Appointments of User", "id", userPrincipal.getId()));
	}
	
	@PostMapping("/doc/infos")
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<?> editDocInfos(@CurrentUser UserPrincipal userPrincipal,
			@RequestBody DoctorUpdateRequest doctorUpdateRequest) {
		System.out.println("start the update");
		try {
			doctorService.updateDoctor(doctorUpdateRequest);
			
		} catch (Exception e) {
			System.out.println(e);
			return ResponseEntity.unprocessableEntity().build();
		}
        
		return ResponseEntity.ok().body(new ApiResponse(true, "Doctor infos updated successfully@"));
	}
	
	@PutMapping("/doc/schedule")
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<?> setSchedule(@CurrentUser UserPrincipal userPrincipal,@RequestBody Schedule schedule) throws NotFoundException {
		Optional<User> user= userRepository.findById(userPrincipal.getId());
		Optional<Doctor> doctorOptional=docteurRepository.findById(user.get().getDoctor().getId());
		Doctor doctor= doctorOptional.orElseThrow(()-> new NotFoundException("doc not found"));
		doctor.setSchedule(schedule.getValue());
		doctor.setShiftDuration(schedule.getShiftDuration());
		docteurRepository.save(doctor);
		
		return ResponseEntity.ok().body(new ApiResponse(true, "Doctor schedule updated successfully@"));
	}

	@GetMapping("/doc/schedule")
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<?> getSchedule(@CurrentUser UserPrincipal userPrincipal) throws NotFoundException {
		Optional<User> user= userRepository.findById(userPrincipal.getId());
		Optional<Doctor> doctorOptional=docteurRepository.findById(user.get().getDoctor().getId());
		Doctor doctor= doctorOptional.orElseThrow(()-> new NotFoundException("doc not found"));
		Optional<ScheduleView> schedule=docteurRepository.findScheduleById(user.get().getDoctor().getId());
		return ResponseEntity.status(200).body(schedule);
	}
	
}
