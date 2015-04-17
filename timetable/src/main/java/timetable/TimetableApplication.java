package timetable;

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
		return (evt)-> Arrays.asList(
				"Yoga,Boxercise".split(","))
				.forEach(
						e-> {
							eventRepository.save(new Event(e));
						});				
	}
	
    public static void main(String[] args) {
        SpringApplication.run(TimetableApplication.class, args);
    }
}
