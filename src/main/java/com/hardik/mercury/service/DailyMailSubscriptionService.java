package com.hardik.mercury.service;

import org.json.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.hardik.mercury.dto.DailyMailSubscriptionCreationRequest;
import com.hardik.mercury.mail.EmailService;
import com.hardik.mercury.quartz.DailyMailSubscriptionScheduler;
import com.hardik.mercury.quartz.trigger.DailyMailSubscriptionTriggerFactory;
import com.hardik.mercury.utility.ShitQuoteUtility;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class DailyMailSubscriptionService {

	private final DailyMailSubscriptionTriggerFactory dailyMailSubscriptionTriggerFactory;
	private final DailyMailSubscriptionScheduler dailyMailSubscriptionScheduler;
	private final EmailService emailService;

	public ResponseEntity<?> subscribe(
			final DailyMailSubscriptionCreationRequest dailyMailSubscriptionCreationRequest) {
		final var response = new JSONObject();
		final var trigger = dailyMailSubscriptionTriggerFactory
				.generateTrigger(dailyMailSubscriptionCreationRequest.getEmailId());
		dailyMailSubscriptionScheduler.addTriggerInDailyMailSubscriptionService(trigger);
		emailService.sendEmail(dailyMailSubscriptionCreationRequest.getEmailId(),
				"Successfully Subscribed to daily mail subscription service, here is your first quote",
				ShitQuoteUtility.get());
		response.put("message", dailyMailSubscriptionCreationRequest.getEmailId()
				+ " has been successfully subscribed to daily mail subscription seervice");
		return ResponseEntity.ok(response.toString());
	}

	public ResponseEntity<?> unsubscribe(final String emailId) {
		final var response = new JSONObject();
		if (!isSubscribed(emailId)) {
			response.put("message", "Given emailId has not been subscribed to the daily mail subscription service");
			return ResponseEntity.badRequest().body(response.toString());
		}
		dailyMailSubscriptionScheduler.removeTrigger(emailId);
		response.put("message", "Unsubscribed from daily mail subscription service");
		return ResponseEntity.ok(response.toString());
	}

	public ResponseEntity<?> retreiveStatus(final String emailId) {
		final var response = new JSONObject();
		response.put("Status", isSubscribed(emailId) ? "Subscribed" : "Not Subscribed");
		return ResponseEntity.ok(response.toString());
	}

	private Boolean isSubscribed(final String emailId) {
		return dailyMailSubscriptionScheduler.triggerWithEmailScheduled(emailId);
	}

}
