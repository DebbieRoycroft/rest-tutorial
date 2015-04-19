package timetable_mvc_client.controllers;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import timetable_mvc_client.model.Timetable;
import timetable_mvc_client.model.TimetableClass;

@Controller
public class TimetableController {

	@RequestMapping("/timetable")
	public String showFullTimetable(Model model){
		model.addAttribute("timetable", this.getFullTimetable());
		return "full_timetable";
	}

	private Timetable getFullTimetable() {
		Timetable fullTimetable = new Timetable();
		TimetableClass class1 = new TimetableClass();
		class1.setStartTime(Date.from(Instant.now()));
		class1.setTitle("Spin");
		TimetableClass class2 = new TimetableClass();
		class2.setStartTime(Date.from(Instant.now()));
		class2.setTitle("Aerobics");
		List<TimetableClass> classes = new ArrayList<>();
		classes.add(class1);
		classes.add(class2);
		fullTimetable.setClasses(classes);
		return fullTimetable;
	}
}
