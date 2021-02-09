package org.naim.doctoo.repository;

import org.naim.doctoo.model.DoctorInscription;
import org.naim.doctoo.repository.projection.DoctorView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "doctorsInscription", path = "doctorsInscription",excerptProjection = DoctorView.class)
public interface DocteurInscriptionRepository extends JpaRepository<DoctorInscription, Long>  {

	
	@Override
	DoctorInscription save(DoctorInscription doctorInscription);

	boolean existsByEmail(String email);
	
	

}
