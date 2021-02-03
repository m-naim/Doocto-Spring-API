package org.naim.doctoo.service;

import java.util.Date;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.naim.doctoo.exception.ResourceNotFoundException;
import org.naim.doctoo.model.Appointment;
import org.naim.doctoo.model.User;
import org.naim.doctoo.repository.AppointmentRepository;
import org.naim.doctoo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class EmailService {
	@Autowired
	private AppointmentRepository appointmentRepository;
	
	@Autowired
	private UserRepository userRepository;

	/*
	 * task=1 => send mail annulation
	 * task = 2 => send mail confirmation
	 * 
	 * 
	 * */
	public void sendmail(long id,int task) throws MessagingException  {//plus tard il faut recevoir l'objet user et doctor pour prendre les info et personaliser les emails
		   Properties props = new Properties();
		   props.put("mail.smtp.auth", "true");
		   props.put("mail.smtp.starttls.enable", "true");
		   props.put("mail.smtp.host", "smtp.gmail.com");
		   props.put("mail.smtp.port", "587");
		   
		   Session session = Session.getInstance(props, new javax.mail.Authenticator() {
		      protected PasswordAuthentication getPasswordAuthentication() {
		         return new PasswordAuthentication("dzsante.algerie@gmail.com","naimyoucef");
		      }
		   });
		   
		   String footer = "<div><p><b>Pour nous contacter par mail : dzsante.algerie@gmail.com</b></p>\r\n<p><b>&copy;Copyright 2020 <a href=\"https://doocto.netlify.app/\">dzsante.com</a></b></p></div>";
		   	 if(task ==1) {
		   		 
		   		Appointment appointment = appointmentRepository.findById(id)
				        .orElseThrow(() -> new ResourceNotFoundException("appointment" ,"id",id));
		   		
		   		
		   		
		   		
		   		String nomDoc = appointment.getDoctor().getNomProfessionel();
				String nomPatient = appointment.getUser().getName();
				Date date = appointment.getDate();
				String emailPatient = appointment.getUser().getEmail();
				long idDoctor = appointment.getDoctor().getId();
				User docRp = userRepository.findById(idDoctor)
				        .orElseThrow(() -> new ResourceNotFoundException("Doctor" ,"idDoctor",idDoctor));
				
				String emailDoctor = docRp.getEmail();
				
				
				Message msgToPatient = new MimeMessage(session);
				msgToPatient.setFrom(new InternetAddress("dzsante.algerie@gmail.com", true));
				msgToPatient.setRecipients(Message.RecipientType.TO, InternetAddress.parse(emailPatient));
				msgToPatient.setSubject("Annulation de rendez-vous. Important!");
				msgToPatient.setContent("<p>Bonjour "+nomPatient+",</p><p>Votre annulation du rendez-vous du "+date+" est bien tenue en compte avec le docteur <b>"+nomDoc+".</b></p>"+footer, "text/html");
				Transport.send(msgToPatient); 
				   
				Message msgToDoctor = new MimeMessage(session);
				msgToDoctor.setFrom(new InternetAddress("dzsante.algerie@gmail.com", true));
				msgToDoctor.setRecipients(Message.RecipientType.TO, InternetAddress.parse(emailDoctor));
				msgToDoctor.setSubject("Annulation de rendez-vous. Important!");
				msgToDoctor.setContent("<p>Bonjour "+nomDoc+",</p><p>Votre Patient <b>"+nomPatient+ " </b>vient d'annuler son rendez-vous prevu pour le "+date+".</p>"+footer, "text/html");
				Transport.send(msgToDoctor);
		   	 }  

	}
}
