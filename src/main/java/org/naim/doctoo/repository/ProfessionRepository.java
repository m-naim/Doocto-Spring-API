package org.naim.doctoo.repository;

import java.util.List;

import org.naim.doctoo.model.Profession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "professions", path = "professions")
public interface ProfessionRepository extends JpaRepository<Profession, Long>{
	List<Profession> findByProfessionContaining(@Param("name") String name) ;
}
