package org.naim.doctoo.repository.projection;

import org.naim.doctoo.model.Doctor;
import org.naim.doctoo.model.Location;
import org.springframework.data.rest.core.config.Projection;

@Projection(name = "LocationView",types = Location.class)
public interface LocationView {
	public String getDaira();
	public String getWilaya();
}
