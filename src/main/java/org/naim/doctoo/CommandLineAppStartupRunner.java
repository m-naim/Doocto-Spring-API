package org.naim.doctoo;

import org.naim.doctoo.AlgerieLocation.controller.SendMailRs;
import org.naim.doctoo.service.DataLoaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class CommandLineAppStartupRunner implements CommandLineRunner {
    @Autowired
    private  DataLoaderService data;

    @Autowired
 	public static JavaMailSender javaMailSender;
    
    @Override
    public void run(String...args) throws Exception {
    
    
 	data.populateProfessions();
 	data.populateLocations();
	data.populateDoctors();
    }
}