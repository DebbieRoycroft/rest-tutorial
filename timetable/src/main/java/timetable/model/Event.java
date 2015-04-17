package timetable.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Event {
	@Id
	@GeneratedValue
	private Long id;
	private String title;

	public Event() {}
	
	public Event(String title) {
		this.title = title;
	}

	public Long getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

}
