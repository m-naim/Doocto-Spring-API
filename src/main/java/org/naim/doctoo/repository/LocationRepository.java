package org.naim.doctoo.repository;

import java.util.List;
import java.util.Optional;

import org.naim.doctoo.model.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

@RepositoryRestResource(collectionResourceRel = "locations", path = "location")
public interface LocationRepository extends JpaRepository<Location, Long>  {
	
	@RestResource(path = "name")
	List<Location> findByDairaContainingIgnoreCase(@Param("location") String location );
	
	
	Optional<Location> findByDaira(String daira);

}
