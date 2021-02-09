package org.naim.doctoo.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.naim.doctoo.model.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
	Optional<List<Appointment>> findByUserId(@Param("id") Long id);
	Optional<List<Appointment>> findByDoctorId(@Param("id") Long id);
	
	@Query(value = "SELECT * FROM Appointment a WHERE DATE(date) = ?1", nativeQuery = true)
	Optional<List<Appointment>> findByDate(LocalDate date);
}
