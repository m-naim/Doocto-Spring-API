package org.naim.doctoo.repository.projection;

import org.naim.doctoo.model.Location;
import org.springframework.data.rest.core.config.Projection;

@Projection(name = "ProfessionView",types = Location.class)
public interface ProfessionView {
	String getProfession();
}
