package org.naim.doctoo.service;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.stereotype.Service;


@Service
public class EmailService {

	
	public void sendmail(String emailPatient, String emailDoctor) throws MessagingException  {//plus tard il faut recevoir l'objet user et doctor pour prendre les info et personaliser les emails
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
		   
		   
			   Message msgToPatient = new MimeMessage(session);
			   msgToPatient.setFrom(new InternetAddress("dzsante.algerie@gmail.com", true));
			   msgToPatient.setRecipients(Message.RecipientType.TO, InternetAddress.parse(emailPatient));
			   msgToPatient.setSubject("Annulation de rendez-vous. Important!");
			   msgToPatient.setContent("Vous annulation du rendez-vous est bien tenue en compte avec le docteur Youcef BELAIDI", "text/html");
			   Transport.send(msgToPatient); 
			   
			   Message msgToDoctor = new MimeMessage(session);
			   msgToDoctor.setFrom(new InternetAddress("dzsante.algerie@gmail.com", true));
			   msgToDoctor.setRecipients(Message.RecipientType.TO, InternetAddress.parse(emailPatient));
			   msgToDoctor.setSubject("Annulation de rendez-vous. Important!");
			   msgToDoctor.setContent("Vous Patient Naim Mehnaoui vient d'annuler son rendez-vous prevu pour le 15 juin à 9H", "text/html");
			   Transport.send(msgToDoctor);  

		   		}
}
