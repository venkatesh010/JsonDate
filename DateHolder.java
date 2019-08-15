package com.example.DateProj;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

public class DateHolder {

	@JsonFormat(pattern = "dd-MM-yyyy", timezone = "UTC")
	private LocalDate localDate;

	LocalDate getLocalDate() {
		return localDate;
	}

	void setLocalDate(LocalDate localDate) {
		this.localDate = localDate;
	}
}
