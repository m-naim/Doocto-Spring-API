package org.naim.doctoo.AlgerieLocation.controller;

import javax.mail.MessagingException;
import org.naim.doctoo.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SendMailRs {

	@Autowired
	EmailService es;
	@RequestMapping(value = "/sendemail/{emailDoctor}/{emailPatient}")
	public String sendEmail(@PathVariable("emailDoctor") String emailDoctor,@PathVariable("emailPatient") String emailPatient) throws MessagingException {
		  //es.sendmail(emailPatient, emailDoctor);
	      return "Email sent successfully";
	}   
	
}
	
	
