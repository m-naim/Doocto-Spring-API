package org.naim.doctoo.repository;

import java.util.List;

import org.naim.doctoo.model.Commune;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "communes", path = "communes")
public interface CommuneRepository extends JpaRepository<Commune, Long>  {
	List<Commune> findByNameContaining(@Param("name") String name);
}
