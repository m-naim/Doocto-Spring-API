package org.naim.doctoo.repository;

import java.util.Optional;

import org.naim.doctoo.model.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "appointments", path = "appointments")
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
	Optional<Appointment> findByUserID(@Param("id") Long id);
}
