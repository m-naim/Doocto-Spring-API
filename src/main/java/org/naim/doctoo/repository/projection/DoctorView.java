package org.naim.doctoo.repository.projection;

import org.naim.doctoo.model.Coordonnees;
import org.naim.doctoo.model.Doctor;
import org.naim.doctoo.model.Schedule;
import org.springframework.data.rest.core.config.Projection;

@Projection(name = "DoctorView",types = Doctor.class)
public interface DoctorView {
		
	 Long getId();
	
	 String getCivilite();
	
	 LocationView getLocation();
			
	 String getTelephone();
	
	 Coordonnees getCoordonnees();
	
	 String getAddresse();
	
	 ProfessionView getProfession();
	
	 String getNomProfessionel();
	
	 String getSchedule();
}
