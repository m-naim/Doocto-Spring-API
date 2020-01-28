package org.naim.repository;

import org.naim.model.Docteur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "docteurs", path = "docteurs")
public interface DocteurRepository extends JpaRepository<Docteur, Long>  {

}
