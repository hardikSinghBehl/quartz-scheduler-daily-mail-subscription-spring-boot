package com.hardik.mercury.controller;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.hardik.mercury.dto.DailyMailSubscriptionCreationRequest;
import com.hardik.mercury.service.DailyMailSubscriptionService;

import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/subscription")
public class DailyMailSubscriptionController {

	private final DailyMailSubscriptionService dailyMailSubscriptionService;

	@PostMapping("/create")
	@ResponseStatus(value = HttpStatus.OK)
	@Operation(summary = "Subscribes the given email address to daily mail service")
	public ResponseEntity<?> subscriptionCreationHandler(
			@Valid @RequestBody(required = true) final DailyMailSubscriptionCreationRequest dailyMailSubscriptionCreationRequest) {
		return dailyMailSubscriptionService.subscribe(dailyMailSubscriptionCreationRequest);
	}

	@GetMapping("/status/{emailId}")
	@ResponseStatus(value = HttpStatus.OK)
	@Operation(summary = "Returns whether email-id is subscribed to daily mail service")
	public ResponseEntity<?> subscriptionStatusRetreivalHandler(
			@PathVariable(required = true, name = "emailId") final String emailId) {
		return dailyMailSubscriptionService.retreiveStatus(emailId);
	}

	@DeleteMapping("/cancel/{emailId}")
	@ResponseStatus(value = HttpStatus.OK)
	@Operation(summary = "Unsubscribes the given email address to daily mail service")
	public ResponseEntity<?> subscriptionCancellationHandler(
			@PathVariable(required = true, name = "emailId") final String emailId) {
		return dailyMailSubscriptionService.unsubscribe(emailId);
	}

}
