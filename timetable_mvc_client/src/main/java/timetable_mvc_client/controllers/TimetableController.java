package timetable_mvc_client.controllers;

import java.util.Arrays;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

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
		
		RestTemplate restTemplate = new RestTemplate();	
		HttpEntity<String> httpEntity = new HttpEntity<String>(ClientCredentials.getRequestHeaderWithAuthorization());
		ResponseEntity<TimetableClass[]> response = restTemplate.exchange("http://localhost:8080/timetable",
				HttpMethod.GET, 
				httpEntity, 
				TimetableClass[].class);
		TimetableClass[] classes = response.getBody();
		Timetable fullTimetable = new Timetable();
		fullTimetable.setClasses(Arrays.asList(classes));
		return fullTimetable;
	}
}
