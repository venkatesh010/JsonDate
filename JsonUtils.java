package com.example.DateProj;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatterBuilder;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

public class JsonUtils{



	static DateTimeHolder convertStringToObject(String s, Class<DateTimeHolder> localDateTimeClass){

		ObjectMapper mapper = new ObjectMapper();
		mapper.registerModule(new SimpleModule().addDeserializer(
				LocalDateTime.class,
				new LocalDateTimeDeserializer(new DateTimeFormatterBuilder()
						.appendPattern("dd-MM-yyyy HH:mm").toFormatter())));


		//mapper.registerModule(new JavaTimeModule());
		try {
			return mapper.readValue(s, localDateTimeClass);
		}
		catch (IOException e) {
			e.printStackTrace();
		}

		return null;
	}

	static String convertObjectToString(DateTimeHolder localDateTime){

		ObjectMapper mapper = new ObjectMapper();
		mapper.registerModule(new SimpleModule().addSerializer(
				new LocalDateTimeSerializer(new DateTimeFormatterBuilder()
						.appendPattern("dd-MM-yyyy HH:mm").toFormatter())));

		try {
			return mapper.writeValueAsString(localDateTime);
		}
		catch (JsonProcessingException e) {
			e.printStackTrace();
		}

		return null;
	}
}
