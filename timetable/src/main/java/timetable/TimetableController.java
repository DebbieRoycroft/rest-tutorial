package timetable;

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
		events.add(new Event(1L,"Yoga"));
		events.add(new Event(2L,"Boxercise"));
		return events;
		
	}
}
