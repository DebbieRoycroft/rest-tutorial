package timetable_mvc_client;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TimetableController {

	@RequestMapping("/timetable")
	public String showFullTimetable(){
		return "full_timetable";
	}
}
