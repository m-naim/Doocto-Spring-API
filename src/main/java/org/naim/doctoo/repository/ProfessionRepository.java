package org.naim.doctoo.repository;

import java.util.List;
import java.util.Optional;

import org.naim.doctoo.model.Profession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

@RepositoryRestResource(collectionResourceRel = "professions", path = "professions")
public interface ProfessionRepository extends JpaRepository<Profession, Long>{
	@RestResource(path = "names")
	@Query(nativeQuery = true, value =
		"SELECT * FROM public.Profession WHERE unaccent(profession) iLike unaccent(CONCAT('%',:name,'%'))"
			)
	List<Profession> findByProfessionContainingIgnoreCase(@Param("name") String name);
	
	Optional<Profession> findByProfession(String name) ;
}
