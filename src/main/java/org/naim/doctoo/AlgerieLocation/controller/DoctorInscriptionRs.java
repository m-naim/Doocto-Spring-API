package org.naim.doctoo.AlgerieLocation.controller;

import java.util.List;

import org.naim.doctoo.AlgerieLocation.DAO.DoctorInscriptionRepository;
import org.naim.doctoo.model.Doctor;
import org.naim.doctoo.model.DoctorInscription;
import org.naim.doctoo.repository.DocteurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DoctorInscriptionRs {
	@Autowired
	private DoctorInscriptionRepository doctorsInscriptionRep;
	@Autowired
	private DocteurRepository docRep;
	
	
	@GetMapping(value = "/doctorsInscriptionManager")
	public ResponseEntity<List<DoctorInscription>> getAll() {

		List<DoctorInscription> doctors = (List<DoctorInscription>)doctorsInscriptionRep.getAllDocsInscription();
		if (doctors == null) {
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		} else {
			return new ResponseEntity<List<DoctorInscription>>(doctors, HttpStatus.OK);
		}
	}
	
	
	//http://localhost:5000/doctorsInscriptionManager/validate
	/*
	@PostMapping("doctorsInscriptionManager/validate")
	 void validateDoc(@RequestBody DoctorInscription docteur) {
		 if(docteur.getIsValidate()==0) {
			 Doctor doc = new Doctor();
			 doc.setAddresse(docteur.getAddresse());
			 doc.setCivilite(docteur.getCivilite());
			 doc.setCodePostal(docteur.getCodePostal());
			 doc.setCoordonnees(docteur.getCoordonnees());
			 doc.setLocation(docteur.getLocation());
			 doc.setProfession(docteur.getProfession());
			 doc.setNomProfessionel(docteur.getNomProfessionel());
			 doc.setTelephone(docteur.getTelephone());
			 docRep.save(docteur);	 
		 }
		 
		 
	 }*/
}
