package org.naim.doctoo.repository.projection;

import org.naim.doctoo.model.Doctor;
import org.springframework.data.rest.core.config.Projection;

@Projection(name = "ScheduleView",types = Doctor.class)
public interface ScheduleView {
	public String getSchedule();
}