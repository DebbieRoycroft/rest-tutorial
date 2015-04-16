package timetable;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import java.io.IOException;
import java.nio.charset.Charset;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.context.WebApplicationContext;

import timetable.model.Event;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = TimetableApplication.class)
@WebAppConfiguration
public class TimetableControllerTest {

	private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
			MediaType.APPLICATION_JSON.getSubtype(),
			Charset.forName("utf8"));
	
	private MockMvc mockMvc;
	
	@Autowired
	private WebApplicationContext webApplicationContext;
		
	/*
	@Autowired
	void setConverters(HttpMessageConverter<?>[] converters){
		this.mappingJackson2HttpMessageConverter = Arrays.asList(converters).stream()
				.filter(
					hmc -> hmc instanceof MappingJackson2HttpMessageConverter).findAny().get();
		Assert.assertNotNull("the JSON message converter must not be null",
				this.mappingJackson2HttpMessageConverter);
	}
	*/
	
	@Before 
	public void setup() throws Exception {
		this.mockMvc = webAppContextSetup(webApplicationContext).build();
		
	}
	
	@Test
	public void testEventNotFound() throws Exception {
		mockMvc.perform(get("/timetable/event/5"))
				.andExpect(status().isNotFound());
	}
	
	@Test
	public void readSingleEvent() throws Exception {
		mockMvc.perform(get("/timetable/event/1"))
				.andExpect(status().isOk())
				.andExpect(content().contentType(contentType))
				.andExpect(jsonPath("$.id", is(1)))
				.andExpect(jsonPath("$.title", is("Yoga")));
	}
	
	@Test
	public void readEvents() throws Exception {
		mockMvc.perform(get("/timetable"))
				.andExpect(status().isOk())
				.andExpect(content().contentType(contentType))
				.andExpect(jsonPath("$", hasSize(2)))
				.andExpect(jsonPath("$[0].id", is(1)))
				.andExpect(jsonPath("$[0].title", is("Yoga")))
				.andExpect(jsonPath("$[1].id", is(2)))
				.andExpect(jsonPath("$[1].title", is("Boxercise")));
	}
	
	@Test
	public void createEvent() throws Exception {
		String eventJson = json( new Event(
				4L, "lane swimming"));
		MvcResult result = this.mockMvc.perform(post("/timetable/event")
				.contentType(contentType)
				.content(eventJson))
				.andExpect(status().isCreated())
				.andExpect(content().contentType(contentType))
                .andExpect(jsonPath("$.id", is(4)))
                .andExpect(jsonPath("$.title", is("lane swimming")))
                .andReturn();
		String createdPath = result.getResponse().getHeader("Location");
		this.mockMvc.perform(get(createdPath))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.id", is(4)))
			.andExpect(jsonPath("$.title", is("lane swimming")));
	}
	
	@Test
	public void testDeleteEvent() throws Exception {
		String eventJson = json( new Event(
				5L, "Pilates"));
		MvcResult result = this.mockMvc.perform(post("/timetable/event")
				.contentType(contentType)
				.content(eventJson))
				.andExpect(status().isCreated())
				.andExpect(content().contentType(contentType))
                .andExpect(jsonPath("$.id", is(5)))
                .andExpect(jsonPath("$.title", is("Pilates")))
                .andReturn();
		String createdLocation = result.getResponse().getHeader("Location");
		this.mockMvc.perform(get(createdLocation))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.title", is("Pilates")));
		
		this.mockMvc.perform(delete(createdLocation))
		.andExpect(status().isOk());
		
		this.mockMvc.perform(get(createdLocation))
		.andExpect(status().isNotFound());
	}
	protected String json(Object o) throws IOException {
		ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        return mapper.writeValueAsString(o);
	}

}
