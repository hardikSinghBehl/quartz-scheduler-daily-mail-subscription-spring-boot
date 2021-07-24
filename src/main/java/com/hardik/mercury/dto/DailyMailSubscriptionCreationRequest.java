package com.hardik.mercury.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import lombok.Builder;
import lombok.Getter;
import lombok.extern.jackson.Jacksonized;

@Getter
@Builder
@Jacksonized
public class DailyMailSubscriptionCreationRequest {

	@NotBlank(message = "email-id must not be empty")
	@Email(message = "Id must be a valid email address")
	private final String emailId;

}
