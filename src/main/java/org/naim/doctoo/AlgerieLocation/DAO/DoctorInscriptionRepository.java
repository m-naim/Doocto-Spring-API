package org.naim.doctoo.AlgerieLocation.DAO;

import java.util.List;

import org.naim.doctoo.AlgerieLocation.model.Daira;
import org.naim.doctoo.model.DoctorInscription;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;


public interface DoctorInscriptionRepository extends CrudRepository<Daira, String> {

		//@Query(value = "SELECT d FROM doctors_inscription d")
		@Query(value = "select d from DoctorInscription d")
		public List<DoctorInscription> getAllDocsInscription();

		

		
	}