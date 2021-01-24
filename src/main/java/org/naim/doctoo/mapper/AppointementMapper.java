package org.naim.doctoo.mapper;

import java.util.Date;

import org.naim.doctoo.model.Appointment;

public class AppointementMapper {

	public static Date mapToDate(Appointment appointment) {
		return appointment.getDate();
	}
	
}
