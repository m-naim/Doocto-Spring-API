package org.naim.doctoo.mapper;

import org.naim.doctoo.model.AuthProvider;
import org.naim.doctoo.model.Doctor;
import org.naim.doctoo.model.User;

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
}
