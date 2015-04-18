package timetable;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Arrays;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import timetable.model.EventRepository;
import timetable.model.Event;

@SpringBootApplication
public class TimetableApplication {

	@Bean
	CommandLineRunner init(EventRepository eventRepository) {
		return (evt)-> { 
			eventRepository.save( new Event("Yoga", 
				LocalDateTime.of(2015, Month.APRIL, 23, 19, 00),
				LocalDateTime.of(2015, Month.APRIL, 23, 20, 00)));
			eventRepository.save(new Event("Boxercise", 
				LocalDateTime.of(2015, Month.APRIL, 23, 20, 00),
				LocalDateTime.of(2015, Month.APRIL, 23, 21, 00)));
		};		
	}
	
    public static void main(String[] args) {
        SpringApplication.run(TimetableApplication.class, args);
    }
}
