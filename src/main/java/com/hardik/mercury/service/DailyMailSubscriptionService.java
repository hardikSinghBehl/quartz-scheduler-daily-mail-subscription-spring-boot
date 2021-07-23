package com.hardik.mercury.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.hardik.mercury.dto.DailyMailSubscriptionCreationRequest;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class DailyMailSubscriptionService {

	public ResponseEntity<?> subscribe(
			final DailyMailSubscriptionCreationRequest dailyMailSubscriptionCreationRequest) {
		return null;
	}

	public ResponseEntity<?> unsubscribe(final String emailId) {
		return null;
	}

	public ResponseEntity<?> retreiveStatus(final String emailId) {
		return null;
	}

}
