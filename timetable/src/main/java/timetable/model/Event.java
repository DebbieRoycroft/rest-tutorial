package timetable.model;

public class Event {
	private Long id;
	private String title;

	public Event(Long id, String title) {
		this.id = id;
		this.title = title;
	}

	public Long getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

}
