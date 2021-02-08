package org.naim.doctoo.service;


import java.util.Date;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.naim.doctoo.exception.ResourceNotFoundException;
import org.naim.doctoo.model.Appointment;
import org.naim.doctoo.model.Doctor;
import org.naim.doctoo.model.DoctorInscription;
import org.naim.doctoo.model.User;
import org.naim.doctoo.repository.AppointmentRepository;
import org.naim.doctoo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;



@Service
public class EmailService {
	@Autowired
	private AppointmentRepository appointmentRepository;
	
	@Autowired
	private UserRepository userRepository;

	@Value("${HOST}")
	private String host;
	
	@Value("${EMAILPASSWORD}")
	private String emailPassword;
	

	@Value("${EMAIL}")
	private String email;
	
	@Async
	public void sendmailAnnulation(long id) throws MessagingException  {//plus tard il faut recevoir l'objet user et doctor pour prendre les info et personaliser les emails
		  
		   Properties props = prop();
		   Session session = sessionSourceMail(props);
		   String footer = footer();
		   		 
		   		Appointment appointment = appointmentRepository.findById(id)
				        .orElseThrow(() -> new ResourceNotFoundException("appointment" ,"id",id));
		   			
		   		String nomDoc = appointment.getDoctor().getNomProfessionel();
				String nomPatient = appointment.getUser().getName();
				Date date = appointment.getDate();
				String emailPatient = appointment.getUser().getEmail();
				long idDoctor = appointment.getDoctor().getId();
				
				User docRp = userRepository.findByDoctorId(idDoctor)
				        .orElseThrow(() -> new ResourceNotFoundException("Doctor" ,"idDoctor",idDoctor));
				
				String emailDoctor = docRp.getEmail();
				
				
				Message msgToPatient = new MimeMessage(session);
				msgToPatient.setFrom(new InternetAddress(email, true));
				msgToPatient.setRecipients(Message.RecipientType.TO, InternetAddress.parse(emailPatient));
				msgToPatient.setSubject("Annulation de rendez-vous. Important!");
				msgToPatient.setContent("<p>Bonjour "+nomPatient+",</p><p>Votre annulation du rendez-vous du "+date+" est bien tenue en compte avec le docteur <b>"+nomDoc+".</b></p>"+footer, "text/html");
				Transport.send(msgToPatient); 
				   
				Message msgToDoctor = new MimeMessage(session);
				msgToDoctor.setFrom(new InternetAddress(email, true));
				msgToDoctor.setRecipients(Message.RecipientType.TO, InternetAddress.parse(emailDoctor));
				msgToDoctor.setSubject("Annulation de rendez-vous. Important!");
				msgToDoctor.setContent("<p>Bonjour "+nomDoc+",</p><p>Votre Patient <b>"+nomPatient+ " </b>vient d'annuler son rendez-vous prevu pour le "+date+".</p>"+footer, "text/html");
				Transport.send(msgToDoctor);	  

	}
	@Async
	public void sendmailConfirmationInscriptionDoc(String email,Doctor doctor) throws AddressException, MessagingException{
		
		   Properties props = prop();
		   Session session = sessionSourceMail(props);
		   String footer = footer();
		   String nomDoc = doctor.getNomProfessionel();
		   
		   String emailDoctor = email;  
		   
		   Message msgToDoctorInscription = new MimeMessage(session);
		   msgToDoctorInscription.setFrom(new InternetAddress(email, true));
		   msgToDoctorInscription.setRecipients(Message.RecipientType.TO, InternetAddress.parse(emailDoctor));
		   msgToDoctorInscription.setSubject("Confirmation d'inscription du docteur "+nomDoc+".");
		   msgToDoctorInscription.setContent("<p>Bonjour docteur "+nomDoc+",</p><p>Votre inscription vient d'être confirmée. Il vous suffit de confirmer de votre coté en cliqant sur le lien "
		   		+ "qui se trouve dans l'email que vous avez recu. Merci.</p>"+footer, "text/html");
		   Transport.send(msgToDoctorInscription);	  
	}
	
	@Async
	public void sendmailDemandeInscriptionDoc(DoctorInscription doctorInscription) throws AddressException, MessagingException{
		
		   Properties props = prop();
		   Session session = sessionSourceMail(props);
		   String footer = footer();
		   String nomDocInscription = doctorInscription.getName();
		   Date date = new Date();
		   String emailDoctorInscription = doctorInscription.getEmail();   
		   
		   Message msgToDoctorInscription = new MimeMessage(session);
		   msgToDoctorInscription.setFrom(new InternetAddress(email, true));
		   msgToDoctorInscription.setRecipients(Message.RecipientType.TO, InternetAddress.parse(emailDoctorInscription));
		   msgToDoctorInscription.setSubject("Demande d'inscription du docteur "+nomDocInscription+".");
		   msgToDoctorInscription.setContent("<p>Bonjour docteur "+nomDocInscription+",</p><p>Votre demande d'inscription du "+date+" est bien prise en compte. Nous reviendrons "
		   		+ "vers vous le plus rapidement possible.</p>"+footer, "text/html");
		   Transport.send(msgToDoctorInscription);	  
	}
	
	@Async
	public void sendmailRdvConfirmation(Appointment appointment) throws AddressException, MessagingException{
		
		   Properties props = prop();
		   Session session = sessionSourceMail(props);
		   String footer = footer();
	   			
	   		String nomDoc = appointment.getDoctor().getNomProfessionel();
			String nomPatient = appointment.getUser().getName();
			Date date = appointment.getDate();
			String emailPatient = appointment.getUser().getEmail();
			long idDoctor = appointment.getDoctor().getId();
			User docRp = userRepository.findByDoctorId(idDoctor)
			        .orElseThrow(() -> new ResourceNotFoundException("Doctor" ,"idDoctor",idDoctor));
			
			String emailDoctor = docRp.getEmail();
			
			
			Message msgToPatient = new MimeMessage(session);
			msgToPatient.setFrom(new InternetAddress(email, true));
			msgToPatient.setRecipients(Message.RecipientType.TO, InternetAddress.parse(emailPatient));
			msgToPatient.setSubject("Prise de rendez-vous. Important!");
			msgToPatient.setContent("<p>Bonjour "+nomPatient+",</p><p>Votre rendez-vous du "+date+" est bien tenue en compte avec le docteur <b>"+nomDoc+".</b></p>"+footer, "text/html");
			Transport.send(msgToPatient); 
			   
			Message msgToDoctor = new MimeMessage(session);
			msgToDoctor.setFrom(new InternetAddress(email, true));
			msgToDoctor.setRecipients(Message.RecipientType.TO, InternetAddress.parse(emailDoctor));
			msgToDoctor.setSubject("Prise de rendez-vous. Important!");
			msgToDoctor.setContent("<p>Bonjour "+nomDoc+",</p><p>Le Patient <b>"+nomPatient+ " </b>vient de prendre rendez-vous pour le "+date+".</p>"+footer, "text/html");
			Transport.send(msgToDoctor);	  	  
	}
	
	@Async
	public void sendmailConfirmationUser(String token, User user) throws AddressException, MessagingException{
		
		   Properties props = prop();
		   Session session = sessionSourceMail(props);
		   String footer = footer();
		   
		   String userName = user.getName();
		   Date date = new Date();
		   String emailUser = user.getEmail();   
		   String lien = "Veuillez cliquer sur le lien pour confirmer votre compte : "
		            +host+"/auth/confirm-account?token="+token;
		   
		   Message msgToUserConfirmation = new MimeMessage(session);
		   msgToUserConfirmation.setFrom(new InternetAddress(email, true));
		   msgToUserConfirmation.setRecipients(Message.RecipientType.TO, InternetAddress.parse(emailUser));
		   msgToUserConfirmation.setSubject("Confirmation d'inscription "+userName+".");
		   msgToUserConfirmation.setContent("<p>Bonjour "+userName+",</p> "+lien+footer, "text/html");
		   Transport.send(msgToUserConfirmation);	  
	}
	
	
	
	
	public Properties prop() {
		Properties props = new Properties();
		   props.put("mail.smtp.auth", "true");
		   props.put("mail.smtp.starttls.enable", "true");
		   props.put("mail.smtp.host", "smtp.gmail.com");
		   props.put("mail.smtp.port", "587");
		return props;
	}
	
	public Session sessionSourceMail (Properties props) {
		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
	      protected PasswordAuthentication getPasswordAuthentication() {
	         return new PasswordAuthentication(email,emailPassword);
	      }
	   });
		return session;
	}
	
	public String footer() {
		   
		   String footer = "<div><p><b>Cordialement,</b></p><b>L'Équipe Dz santé.</b><p><b>Pour nous contacter par mail :"+email+"</b></p>\r\n<p><b>&copy;Copyright 2020 <a href=\"https://doocto.netlify.app/\">dzsante.com</a></b></p></div>";
		   return footer;
	}
}
