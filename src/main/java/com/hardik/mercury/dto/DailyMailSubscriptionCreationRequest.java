package com.hardik.mercury.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@JacksonStdImpl
public class DailyMailSubscriptionCreationRequest {

	@NotBlank(message = "email-id must not be empty")
	@Email(message = "Id must be a valid email address")
	private final String emailId;

}
