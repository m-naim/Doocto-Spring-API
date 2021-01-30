package org.naim.doctoo.AlgerieLocation.DAO;

import java.util.List;

import org.naim.doctoo.AlgerieLocation.model.Commune;
import org.naim.doctoo.AlgerieLocation.model.Daira;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;


public interface CommuneRepository extends CrudRepository<Daira, String> {

	// PS: use the name of entity not the table !
	@Query(value = "SELECT c FROM Commune c WHERE c.name = ?1")
	public List<Commune> findByCode(String name);
	//

	
}
