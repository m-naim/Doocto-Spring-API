package org.naim.doctoo.controller;

import java.net.URI;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.validation.Valid;

import org.apache.logging.log4j.Logger;
import org.naim.doctoo.exception.ResourceNotFoundException;
import org.naim.doctoo.mapper.AppointementMapper;
import org.naim.doctoo.model.Appointment;
import org.naim.doctoo.model.Doctor;
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

	@GetMapping("/appointments")
	@PreAuthorize("hasRole('USER')")
	public Optional<List<Appointment>> getUserAppointments(@CurrentUser UserPrincipal userPrincipal) {
		return appointmentRepository.findByUserId(userPrincipal.getId());
	}

	@PostMapping("/appointments")
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<ApiResponse> setUserAppointments(@CurrentUser UserPrincipal userPrincipal,
			@Valid @RequestBody AppointmentRequest appointmentRequest) {

		User user = userRepository.findById(userPrincipal.getId())
				.orElseThrow(() -> new ResourceNotFoundException("User", "id", userPrincipal.getId()));
		Doctor doctor = docteurRepository.findById(appointmentRequest.getDocteurId())
				.orElseThrow(() -> new ResourceNotFoundException("Docteur", "id", appointmentRequest.getDocteurId()));

		Appointment appointment = Appointment.builder().doctor(doctor).motif(appointmentRequest.getMotif()).user(user)
				.date(appointmentRequest.getDate()).build();

		Appointment result = appointmentRepository.save(appointment);
		URI location = ServletUriComponentsBuilder.fromCurrentContextPath().path("/appointments")
				.buildAndExpand(result.getId()).toUri();

		return ResponseEntity.created(location).body(new ApiResponse(true, "Appointment created successfully@"));
	}

	@GetMapping("/doc")
	@PreAuthorize("hasRole('USER')")
	public List<Appointment> getDocAppointments(@CurrentUser UserPrincipal userPrincipal) {
		Optional<User> doc = userRepository.findById(userPrincipal.getId());
		return appointmentRepository.findByDoctorId(doc.get().getDoctor().getId())
				.orElseThrow(() -> new ResourceNotFoundException("Appointments of User", "id", userPrincipal.getId()));
	}

	@GetMapping("/booked")
	public List<Date> getDocBookedDates(@RequestParam Long docId) {
		Optional<List<Appointment>> appointments = appointmentRepository.findByDoctorId(docId);
		if (!appointments.isPresent())
			return Collections.emptyList();
		return appointments.get().stream().map(AppointementMapper::mapToDate).collect(Collectors.toList());

	}
}
