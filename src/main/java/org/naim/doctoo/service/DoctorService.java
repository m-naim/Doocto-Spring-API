package org.naim.doctoo.service;

import java.util.Optional;

import org.naim.doctoo.exception.ResourceNotFoundException;
import org.naim.doctoo.mapper.DoctorMapper;
import org.naim.doctoo.model.Doctor;
import org.naim.doctoo.model.Location;
import org.naim.doctoo.model.Profession;
import org.naim.doctoo.model.User;
import org.naim.doctoo.payload.DoctorUpdateRequest;
import org.naim.doctoo.repository.DocteurRepository;
import org.naim.doctoo.repository.LocationRepository;
import org.naim.doctoo.repository.ProfessionRepository;
import org.naim.doctoo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DoctorService {
	@Autowired
	private ProfessionRepository professionRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private DocteurRepository docteurRepository;
	@Autowired
	private LocationRepository locationRepository;

	public Doctor updateDoctor(DoctorUpdateRequest doctorUpdateRequest) {
		Optional<User> user = userRepository.findByEmail(doctorUpdateRequest.getEmail());
		if(!user.isPresent()) throw new ResourceNotFoundException("doctor", "email", doctorUpdateRequest.getEmail());

		Doctor doctor = user.get().getDoctor();
        DoctorMapper.updateDoctorFromRequest(doctor, doctorUpdateRequest);
        
        addProfession(doctor, doctorUpdateRequest.getProfession());
        addLocation(doctorUpdateRequest.getLocation().getDaira(), doctor);
        System.out.println(doctor);
        System.out.println(doctorUpdateRequest);
        
        doctor= docteurRepository.save(doctor); 
        return doctor;
	}
	
	public void addProfession(Doctor doc, String pro) {
		Optional<Profession> profession = professionRepository.findByProfession(pro);
        if(profession.isPresent())
        	doc.setProfession(profession.get());
        else{
        	Profession newProfession= new Profession();
        	newProfession.setProfession(pro);
        	newProfession=professionRepository.save(newProfession);
        	doc.setProfession(newProfession);
        }
	}
	
	public void addLocation(String daira, Doctor doctor) {
		Optional<Location> optionalLocation = locationRepository.findByDaira(daira);
        if (!optionalLocation.isPresent()) {
            throw new ResourceNotFoundException("location", "daira", daira);
        }
        doctor.setLocation(optionalLocation.get());
	}
}
