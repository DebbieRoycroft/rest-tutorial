package timetable_mvc_client.model;

import java.util.Date;

public class TimetableClass {
	private String title;
	private Date startTime;
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
}
