package timetable.model;

import java.time.LocalDateTime;

public class Event {
	private Long id;
	private String title;
	private LocalDateTime startTime;
	private LocalDateTime endTime;
	
	public Event(Long id, String title, 
			LocalDateTime startTime, LocalDateTime endTime) {
		this.id = id;
		this.title = title;
		this.startTime = startTime;
		this.endTime = endTime;
	}

	public Long getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public LocalDateTime getStartTime() {
		return startTime;
	}

	public LocalDateTime getEndTime() {
		return endTime;
	}
	
}
