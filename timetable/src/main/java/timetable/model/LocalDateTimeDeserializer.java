package timetable.model;

import java.io.IOException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

public class LocalDateTimeDeserializer extends JsonDeserializer<LocalDateTime> {
    @Override
    public LocalDateTime deserialize(JsonParser arg0, 
    		DeserializationContext arg1) throws IOException, JsonProcessingException {
        Instant dateTime = Instant.ofEpochMilli(arg0.getLongValue());
    	return LocalDateTime.ofInstant(dateTime, ZoneId.of("UTC"));
    }
}
