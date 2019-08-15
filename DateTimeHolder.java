package com.example.DateProj;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

public class DateTimeHolder {

	@JsonFormat(pattern = "dd-MM-yyyy HH:mm", timezone = "UTC")
	private LocalDateTime localDateTime;

	LocalDateTime getLocalDateTime() {
		return localDateTime;
	}

	void setLocalDateTime(LocalDateTime localDateTime) {
		this.localDateTime = localDateTime;
	}
}
