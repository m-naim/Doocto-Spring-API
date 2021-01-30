package org.naim.doctoo.repository;

import java.util.List;
import java.util.Optional;

import org.naim.doctoo.model.Doctor;
import org.naim.doctoo.repository.projection.DoctorView;
import org.naim.doctoo.repository.projection.ScheduleView;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;


@RepositoryRestResource(collectionResourceRel = "doctors", path = "doctors",excerptProjection = DoctorView.class)
public interface DocteurRepository extends JpaRepository<Doctor, Long>  {
	
	Doctor findById(@Param("id") long id);
	
	@RestResource(path = "names")
	List<Doctor> findByNomProfessionelContaining(@Param("name") String name) ;
	@RestResource(path = "professions")
	List<Doctor> findByProfessionProfessionContaining(@Param("name") String name) ;
	
	@RestResource(path = "professionsAndLocations")
	List<Doctor> findByProfessionProfessionContainingIgnoreCaseAndLocationDairaContainingIgnoreCase(
			@Param("profession") String profession,
			@Param("location") String location,
			Pageable pageable
			) ;
	

	Optional<ScheduleView> findScheduleById(@Param("id") long id);
	
	//	List<Docteur> findDoteurssByLocationAndProfessionProfessionContaining(@Param("name") String name,@Param("lat") double lat, @Param("lon") double lon, @Param("distance") double distance, Pageable pageable);
	//static final String DISTANCE = "(6371 * acos(cos(radians(:lat)) * cos(radians(m.coordonnees.lat)) * cos(radians(m.coordonnees.lon) - radians(:lon)) + sin(radians(:lat)) * sin(radians(m.coordonnees.lat))))";
	
	static final String DISTANCE = "SQRT(POW(69.1 * (:lat -  m.coordonnees.lat), 2) + POW(69.1 * (m.coordonnees.lon - :lon) * COS(:lat / 57.3), 2))";
	@Query("SELECT m FROM Doctor m WHERE "+DISTANCE+" < :distance ORDER BY "+DISTANCE+" ASC")
	@RestResource(path = "Locations")
	public List<Doctor> findDoteursByLocation(@Param("lat") final double lat, @Param("lon") final double lon, @Param("distance") final double distance, Pageable pageable);

	@RestResource(path = "wilaya")
	List <Doctor> findByLocationWilayaIgnoreCase(@Param("name") String name);
	
}
