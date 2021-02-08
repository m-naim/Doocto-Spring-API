package org.naim.doctoo.mapper;

import java.time.LocalDateTime;
import java.util.Date;

import org.naim.doctoo.model.Appointment;

public class AppointementMapper {

	public static LocalDateTime mapToDate(Appointment appointment) {
		return appointment.getDate();
	}
	
}
