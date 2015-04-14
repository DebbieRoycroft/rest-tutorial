package timetable;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import timetable.model.Event;

@Controller
@RequestMapping("/timetable")
public class TimetableController {

	@RequestMapping(method=RequestMethod.GET)
	public @ResponseBody List<Event> getEvents(){
		List<Event> events = new ArrayList<>();
		events.add(new Event(1L,"Yoga",
				LocalDateTime.of(2015, Month.APRIL, 23, 19, 00),
				LocalDateTime.of(2015, Month.APRIL, 23, 20, 00)));
		events.add(new Event(1L,"Boxercise",
				LocalDateTime.of(2015, Month.APRIL, 23, 20, 00),
				LocalDateTime.of(2015, Month.APRIL, 23, 21, 00)));
		return events;
		
	}
}
