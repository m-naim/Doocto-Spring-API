package org.naim.doctoo.mapper;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import org.naim.doctoo.model.AuthProvider;
import org.naim.doctoo.model.Coordonnees;
import org.naim.doctoo.model.Doctor;
import org.naim.doctoo.model.DoctorInscription;
import org.naim.doctoo.model.Location;
import org.naim.doctoo.model.User;
import org.naim.doctoo.payload.SignUpDoctorRequest;

public class DoctorMapper {

	public static Doctor mapObject(DoctorInterface object) {
		Doctor doctor= new Doctor();
        doctor.setNomProfessionel(object.getName());
        doctor.setAddresse(object.getAdresse());
        doctor.setCivilite(object.getCivilite());
        doctor.setTelephone(object.getTelephone());
        doctor.setCoordonnees(object.getCoordonnees());
		return doctor;
	}
	
	public static DoctorInscription mapObjectInscription(SignUpDoctorRequest object) {
		DoctorInscription doctorInscription= new DoctorInscription();
		doctorInscription.setName(object.getName());
		doctorInscription.setEmail(object.getEmail());
		doctorInscription.setPassword(object.getPassword());
		doctorInscription.setAdresse(object.getAdresse());
		doctorInscription.setCivilite(object.getCivilite());
		doctorInscription.setLocation(object.getLocation());
        doctorInscription.setTelephone(object.getTelephone());
        doctorInscription.setProfession(object.getPassword());
        doctorInscription.setCoordonnees(object.getCoordonnees());
		return doctorInscription;
	}
	public static void updateDoctorFromRequest(Doctor doc,DoctorInterface req) {
		doc.setNomProfessionel(req.getName());
		doc.setAddresse(req.getAdresse());
		doc.setCivilite(req.getCivilite());
		doc.setTelephone(req.getTelephone());
		doc.setCoordonnees(req.getCoordonnees());
		
	}
}
