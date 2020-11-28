package org.naim.doctoo.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.apache.logging.log4j.Logger;
import org.naim.doctoo.exception.ResourceNotFoundException;
import org.naim.doctoo.model.Appointment;
import org.naim.doctoo.model.Docteur;
import org.naim.doctoo.model.User;
import org.naim.doctoo.payload.ApiResponse;
import org.naim.doctoo.payload.AppointmentRequest;
import org.naim.doctoo.repository.AppointmentRepository;
import org.naim.doctoo.repository.DocteurRepository;
import org.naim.doctoo.repository.UserRepository;
import org.naim.doctoo.security.CurrentUser;
import org.naim.doctoo.security.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public class AppointmentController {

	@Autowired
	private AppointmentRepository appointmentRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private DocteurRepository docteurRepository;

	private Object object;

	private Object object2;


	@GetMapping("/appointments")
	@PreAuthorize("hasRole('USER')")
	public List<Appointment> getUserAppointments(@CurrentUser UserPrincipal userPrincipal) {
		return appointmentRepository.findByUserId(userPrincipal.getId())
				.orElseThrow(() -> new ResourceNotFoundException("Appointments of User", "id", userPrincipal.getId()));
	}

	@PostMapping("/appointments")
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<ApiResponse> setUserAppointments(@CurrentUser UserPrincipal userPrincipal, @Valid @RequestBody AppointmentRequest appointmentRequest) {

		User user = userRepository.findById(userPrincipal.getId())
				.orElseThrow(() -> new ResourceNotFoundException("User", "id", userPrincipal.getId()));
		Docteur docteur= docteurRepository.findById(appointmentRequest.getDocteurId())
				.orElseThrow(() -> new ResourceNotFoundException("Docteur", "id", appointmentRequest.getDocteurId()));

		Appointment appointment=new Appointment(docteur,appointmentRequest.getDate(),user);

		Appointment result = appointmentRepository.save(appointment);
		URI location = ServletUriComponentsBuilder
				.fromCurrentContextPath().path("/appointments")
				.buildAndExpand(result.getId()).toUri();

		return ResponseEntity.created(location)
				.body(new ApiResponse(true, "Appointment created successfully@"));
	}

	@GetMapping("/doc")
	@PreAuthorize("hasRole('USER')")
	public List<Appointment> getDocAppointments(@CurrentUser UserPrincipal userPrincipal) {
		Optional<User> doc= userRepository.findById(userPrincipal.getId());
		return appointmentRepository.findByDocteurId(doc.get().getDocteur().getId())
				.orElseThrow(() -> new ResourceNotFoundException("Appointments of User", "id", userPrincipal.getId()));
	}
}
