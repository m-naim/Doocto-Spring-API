package org.naim.doctoo.scheduled;

import org.naim.doctoo.exception.ResourceNotFoundException;
import org.naim.doctoo.model.Appointment;
import org.naim.doctoo.repository.AppointmentRepository;
import org.naim.doctoo.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;

import javax.mail.MessagingException;

@Component
public class ScheduledTasks {

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
    @Autowired
    private AppointmentRepository appointmentRepository;
    
    @Autowired
    EmailService es;
    
    @Scheduled(cron = "0 0 12 ? * *" )
    public void scheduleTaskWithFixedRate() throws MessagingException {
        
        LocalDate today = LocalDate.now();
        LocalDate tomorrow = today.plus(1, ChronoUnit.DAYS);
        List<Appointment> appointmentBydate = appointmentRepository.findByDate(tomorrow)
        		.orElseThrow(() -> new ResourceNotFoundException("appointment" ,"s",1));
        
        es.sendmailReminder(appointmentBydate);

    }
}