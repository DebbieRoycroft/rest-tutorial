package timetable;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import timetable.model.Event;
import timetable.model.EventRepository;

@Controller
@RequestMapping("/timetable")
public class TimetableController {

	@Autowired
	EventRepository eventRepo;
		
	@RequestMapping(method=RequestMethod.GET)
	public @ResponseBody Collection<Event> getEvents(){
		return eventRepo.findAll();
		
	}
}
