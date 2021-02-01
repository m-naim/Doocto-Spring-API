package org.naim.doctoo.AlgerieLocation.DAO;

import java.util.List;

import org.naim.doctoo.AlgerieLocation.model.Commune;
import org.naim.doctoo.AlgerieLocation.model.Wilaya;
import org.naim.doctoo.model.Doctor;
import org.naim.doctoo.model.Location;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;


public interface WilayaRepository extends CrudRepository<Wilaya, Integer>{

	
	
	@Query(value = "SELECT DISTINCT(w.wilaya) FROM Location w ORDER BY w.wilaya")
	public List<String> getAllWilaya();
	
	
	// PS: use the name of entity not the table !
	// il suffit de rajouter la jointure pour avoir que les docs d'une wilaya
		//@Query(value = "select p from Projet p LEFT  JOIN p.commune c LEFT JOIN c.departement d  where c.nom=?1 ")
		  @Query(value = "select d from Doctor d ")
		public List<Doctor> findDoctorByWilayaId();
		//

}
