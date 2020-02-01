package org.naim.doctoo.repository;

import java.util.List;

import org.naim.doctoo.model.Docteur;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "docteurs", path = "docteurs")
public interface DocteurRepository extends JpaRepository<Docteur, Long>  {
	List<Docteur> findByNomProfessionelContaining(@Param("name") String name) ;
	
	List<Docteur> findByProfessionProfessionContaining(@Param("name") String name) ;
	
	static final String DISTANCE = "(6371 * acos(cos(radians(:lat)) * cos(radians(m.coordonnees.lat)) * cos(radians(m.coordonnees.lon) - radians(:lon)) + sin(radians(:lat)) * sin(radians(m.coordonnees.lat))))";
	@Query("SELECT m FROM Docteur m WHERE "+DISTANCE+" < :distance ORDER BY "+DISTANCE+" DESC")
	public List<Docteur> findEntitiesByLocation(@Param("lat") final double lat, @Param("lon") final double lon, @Param("distance") final double distance, Pageable pageable);
}
