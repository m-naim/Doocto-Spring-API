package org.naim.repository;

import org.naim.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "people", path = "people")
public interface DocteurRepository extends JpaRepository<Person, Long>  {

}
