package org.naim.doctoo.AlgerieLocation.controller;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.naim.doctoo.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SendMailRs {

	@Autowired
	EmailService es;
	@RequestMapping(value = "/sendemail/{emailDoctor}/{emailPatient}")
	public String sendEmail(@PathVariable("emailDoctor") String emailDoctor,@PathVariable("emailPatient") String emailPatient) throws MessagingException {
		  es.sendmail(emailPatient, emailDoctor);
	      return "Email sent successfully";
	}   
	
}
	
	
