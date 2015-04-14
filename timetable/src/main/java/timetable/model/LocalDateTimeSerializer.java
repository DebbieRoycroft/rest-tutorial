package timetable.model;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class LocalDateTimeSerializer extends JsonSerializer<LocalDateTime> {
    @Override
    public void serialize(LocalDateTime arg0, 
    		JsonGenerator arg1, 
    		SerializerProvider arg2) 
    				throws IOException, JsonProcessingException {
    	long secsFromEpoch = arg0.toEpochSecond(ZoneOffset.UTC);
        arg1.writeNumber(secsFromEpoch);
    }

}

