package org.naim.doctoo.repository.projection;

import org.naim.doctoo.model.Commune;
import org.naim.doctoo.model.Coordonnees;
import org.naim.doctoo.model.Docteur;
import org.springframework.data.rest.core.config.Projection;

@Projection(name = "inlineProfession",types = Docteur.class)
public interface inlineProfession {
	Long getId();
	
	 String getCivilite();
	
	 Commune getCommune();
			
	 String getTelephone();
	
	 Coordonnees getCoordonnees();
	
	 String getAddresse();

	 String getCodePostal();
	
	 String getProfessionName();
	
	 String getNomProfessionel();
	
}
