package org.naim.doctoo.AlgerieLocation.controller;

import java.util.List;

import org.naim.doctoo.AlgerieLocation.DAO.CommuneRepository;
import org.naim.doctoo.AlgerieLocation.model.Commune;
import org.naim.doctoo.AlgerieLocation.model.Daira;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class CommuneRs {
	@Autowired // This means to get the bean called userRepository
	// Which is auto-generated by Spring, we will use it to handle the data
	private CommuneRepository communeRep;

	@GetMapping(value = "/communes")
	public ResponseEntity<List<Commune>> getAll() {

		List<Commune> communes = (List<Commune>)communeRep.findByCode("Boghni");
		if (communes == null) {
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		} else {
			return new ResponseEntity<List<Commune>>(communes, HttpStatus.OK);
		}
	}
	
	

}