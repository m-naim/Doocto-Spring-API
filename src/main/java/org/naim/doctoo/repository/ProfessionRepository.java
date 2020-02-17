package org.naim.doctoo.repository;

import java.util.Optional;

import org.naim.doctoo.model.Profession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "professions", path = "professions")
public interface ProfessionRepository extends JpaRepository<Profession, Long>{
	Optional<Profession> findByProfessionContaining(@Param("name") String name) ;
}
