package org.naim.doctoo.repository;

import java.util.List;
import java.util.Optional;

import org.naim.doctoo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "users", path = "users",exported = false)
public interface UserRepository extends JpaRepository<User, Long> {
	Optional<User> findById(long id);
	List<User> findByName(@Param("name") String name);
	Optional<User> findByEmail(String email);
	Boolean existsByEmail(String email);
	
	User findOneByName(String name);

}
