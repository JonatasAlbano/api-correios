package com.joalbano.correiosapi.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class ResponseDTO {
	
	@JsonProperty("max_date")
	private Date maxDate;

}
