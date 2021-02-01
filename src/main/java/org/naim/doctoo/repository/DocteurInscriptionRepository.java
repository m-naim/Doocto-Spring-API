package org.naim.doctoo.repository;

import java.util.List;

import org.naim.doctoo.model.Doctor;
import org.naim.doctoo.model.DoctorInscription;
import org.naim.doctoo.repository.projection.DoctorView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "doctorsInscription", path = "doctorsInscription",excerptProjection = DoctorView.class)
public interface DocteurInscriptionRepository extends JpaRepository<DoctorInscription, Long>  {

	
	DoctorInscription save(DoctorInscription doctorInscription);
	
	

}
