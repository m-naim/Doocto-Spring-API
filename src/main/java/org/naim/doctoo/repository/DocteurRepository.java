package org.naim.doctoo.repository;

import java.util.List;
import java.util.Optional;

import org.naim.doctoo.model.Docteur;
import org.naim.doctoo.repository.projection.inlineProfession;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;


@RepositoryRestResource(collectionResourceRel = "docteurs", path = "docteurs",excerptProjection = inlineProfession.class)
public interface DocteurRepository extends JpaRepository<Docteur, Long>  {
	
	Docteur findById(@Param("id") long id);
	
	@RestResource(path = "names")
	List<Docteur> findByNomProfessionelContaining(@Param("name") String name) ;
	@RestResource(path = "professions")
	List<Docteur> findByProfessionProfessionContaining(@Param("name") String name) ;
	
	@RestResource(path = "professionsAndCommunes")
	List<Docteur> findByProfessionProfessionContainingAndCommuneNameContaining(
			@Param("profession") String profession,
			@Param("commune") String commune,
			Pageable pageable
			) ;
	
//	List<Docteur> findDoteurssByLocationAndProfessionProfessionContaining(@Param("name") String name,@Param("lat") double lat, @Param("lon") double lon, @Param("distance") double distance, Pageable pageable);

	static final String DISTANCE = "(6371 * acos(cos(radians(:lat)) * cos(radians(m.coordonnees.lat)) * cos(radians(m.coordonnees.lon) - radians(:lon)) + sin(radians(:lat)) * sin(radians(m.coordonnees.lat))))";
	@Query("SELECT m FROM Docteur m WHERE "+DISTANCE+" < :distance ORDER BY "+DISTANCE+" ASC")
	@RestResource(path = "Locations")
	public List<Docteur> findDoteursByLocation(@Param("lat") final double lat, @Param("lon") final double lon, @Param("distance") final double distance, Pageable pageable);


}
