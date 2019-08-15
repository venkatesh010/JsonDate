package com.example.DateProj;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatterBuilder;

import javax.annotation.PostConstruct;

import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class DateProjApplication {

	public static void main(String[] args) {
		SpringApplication.run(DateProjApplication.class, args);
	}

	//"2019-08-15T14:48:21.673Z"
	@GetMapping("/instant")
	public Instant hello1(){
		return Instant.now();
	}

	//"2019-08-15"
	@GetMapping("/localDate")
	public LocalDate hello2(){
		return LocalDate.now();
	}

	//"2019-08-15T20:19:21.559"
	@GetMapping("/localDateTime")
	public LocalDateTime hello3(){
		return LocalDateTime.now();
	}

	//"2019-08-15T20:19:42.936+05:30"
	@GetMapping("/zonedDateTime")
	public ZonedDateTime hello4(){
		return ZonedDateTime.now();
	}

	//"2019-08-15T14:50:06.743Z"
	@GetMapping("/zonedDateTimeWithZoneId")
	public ZonedDateTime hello5(){
		return ZonedDateTime.now(ZoneId.of("UTC"));
	}

	//"2019-08-15"
	@GetMapping("/localDateWithZoneId")
	public LocalDate hello6(){
		return LocalDate.now(ZoneId.of("UTC"));
	}


	//"15-08-2019 15:54" (UTC Date Time, so it assures that even LocalDate with timezone will give UTC Date)
	//Step 1: Generating Date in UTC timezone
	//Step2: Serializing (conversion to string) by using your custom format specified in PostConstruct bean
	@GetMapping("/localDateTimeWithZoneId")
	public LocalDateTime hello9(){
		return LocalDateTime.now(ZoneId.of("UTC"));
	}

	//
	@PostMapping("/postLocalDateWithZoneId")
	public LocalDate hello7(@RequestBody DateHolder dateHolder){
		return dateHolder.getLocalDate();
	}

	@PostMapping("/postLocalDateTimeWithZoneId")
	public LocalDateTime hello8(@RequestBody DateTimeHolder dateTimeHolder){
		return dateTimeHolder.getLocalDateTime();
	}

	@GetMapping("/jsonUtils")
	public DateTimeHolder hello10(){
		DateTimeHolder dateTimeHolder = JsonUtils.convertStringToObject("{\n"
				+ "\t\"localDateTime\": \"15-08-2019 09:34\"\n"
				+ "}", DateTimeHolder.class);
		String dateTimeString = JsonUtils.convertObjectToString(dateTimeHolder);
		return dateTimeHolder;
	}

	@Autowired
	private Jackson2ObjectMapperBuilder builder;

	@PostConstruct
	public void postConstruct() {
		this.builder.serializers(new LocalDateSerializer(new DateTimeFormatterBuilder()
				.appendPattern("dd-MM-yyyy").toFormatter()),
				new LocalDateTimeSerializer(new DateTimeFormatterBuilder()
						.appendPattern("dd-MM-yyyy HH:mm").toFormatter())
				);
	}
}
