package org.naim.doctoo.scheduled;

import org.naim.doctoo.exception.ResourceNotFoundException;
import org.naim.doctoo.model.Appointment;
import org.naim.doctoo.repository.AppointmentRepository;
import org.naim.doctoo.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.List;

import javax.mail.MessagingException;

@Component
public class ScheduledTasks {

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
    @Autowired
    private AppointmentRepository appointmentRepository;
    
    @Autowired
    EmailService es;
    
    @Autowired
    RestTemplate restTemplate;
    
    @Scheduled(cron = "0 0 12 ? * *" )
    public void scheduleTaskWithFixedRate() throws MessagingException {
    	LocalDateTime time = LocalDateTime.now();
    	System.out.println(time+" :sending rappels");
    	
        LocalDate today = LocalDate.now();
        LocalDate tomorrow = today.plus(1, ChronoUnit.DAYS);
        List<Appointment> appointmentBydate = appointmentRepository.findByDate(tomorrow)
        		.orElseThrow(() -> new ResourceNotFoundException("appointment" ,"s",1));
        
        es.sendmailReminder(appointmentBydate);

    }
    
    
    @Scheduled(fixedRate = 1500000)
    public void wakeUp() {
    	LocalDateTime today = LocalDateTime.now();
    	System.out.println(today+" :waking up");
    	
    	 HttpHeaders headers = new HttpHeaders();
         headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
         HttpEntity <String> entity = new HttpEntity<String>(headers);
         try {
        	 HttpStatus response = restTemplate.exchange("http://localhost:5000/user/me", HttpMethod.GET, entity, String.class).getStatusCode();			
		} catch (Exception e) {
			// TODO: handle exception
		}
    }
}