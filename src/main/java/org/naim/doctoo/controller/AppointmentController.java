package org.naim.doctoo.controller;

import javax.validation.Valid;

import org.naim.doctoo.exception.ResourceNotFoundException;
import org.naim.doctoo.model.Appointment;
import org.naim.doctoo.model.Docteur;
import org.naim.doctoo.model.User;
import org.naim.doctoo.payload.AppointmentRequest;
import org.naim.doctoo.repository.AppointmentRepository;
import org.naim.doctoo.repository.DocteurRepository;
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
public class AppointmentController {

	@Autowired
    private AppointmentRepository appointmentRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private DocteurRepository docteurRepository;

	
	 @GetMapping("/Appointments")
	    @PreAuthorize("hasRole('USER')")
	    public Appointment getUserAppointments(@CurrentUser UserPrincipal userPrincipal) {
	        return appointmentRepository.findByUserID(userPrincipal.getId())
	                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userPrincipal.getId()));
	    }
	 
	 @PostMapping("/Appointments")
	    @PreAuthorize("hasRole('USER')")
	    public void setUserAppointments(@CurrentUser UserPrincipal userPrincipal, @Valid @RequestBody AppointmentRequest appointmentRequest) {
	         User user = userRepository.findById(userPrincipal.getId())
	                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userPrincipal.getId()));
	         Docteur docteur= docteurRepository.findById(appointmentRequest.getDocteurID())
	        		 .orElseThrow(() -> new ResourceNotFoundException("Docteur", "id", appointmentRequest.getDocteurID()));
	         
	         Appointment appointment=new Appointment();
	         appointment.setDate(appointmentRequest.getDate());
	         appointment.setUser(user);
	         appointment.setDocteur(docteur);
	         
	         appointmentRepository.save(appointment);
	    }
}
