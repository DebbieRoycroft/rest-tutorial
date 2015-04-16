package timetable;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import timetable.model.Event;

@Controller
@RequestMapping("/timetable")
public class TimetableController {

	private Map<Long,Event> events;
	private final AtomicLong counter = new AtomicLong();
	
	public TimetableController(){
		events = new HashMap<>();
		events.put(1L, new Event(1L,"Yoga"));
		events.put(2L, new Event(2L,"Boxercise"));
		counter.set(2L); //set the counter to the ID of the last added Event
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public @ResponseBody Collection<Event> getEvents(){
		return events.values();
		
	}
	
	@RequestMapping(value="/event/{id}", method=RequestMethod.GET)
	public @ResponseBody Event getEventById(@PathVariable Long id){
		Event result = events.get(id);
		if (result == null){
			throw new EventNotFoundException(id);
		}
		return result;
	}
	
	@RequestMapping(value="/event", method=RequestMethod.POST)
	public ResponseEntity<?> addEvent(@RequestBody Event evt){
		Long id = counter.incrementAndGet();
		events.put(id, evt);
		
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setLocation(ServletUriComponentsBuilder
				.fromCurrentRequest().path("/{id}")
				.buildAndExpand(id).toUri());
		return new ResponseEntity<>(evt,httpHeaders, HttpStatus.CREATED);
	}
	
	@RequestMapping(value="/event/{id}", method=RequestMethod.DELETE)
	public void deleteEvent(@PathVariable Long id){
		events.remove(id);
	}
}

@ResponseStatus(HttpStatus.NOT_FOUND)
class EventNotFoundException extends RuntimeException {
	private static final long serialVersionUID = -9174199038443186877L;

	public EventNotFoundException(Long eventId){
		super("could not find event '" + eventId + "'.");
	}
}
