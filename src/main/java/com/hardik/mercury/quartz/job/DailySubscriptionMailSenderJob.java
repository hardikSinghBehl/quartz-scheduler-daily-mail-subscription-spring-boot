package com.hardik.mercury.quartz.job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.stereotype.Component;

import com.hardik.mercury.mail.EmailService;
import com.hardik.mercury.utility.ShitQuoteUtility;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@AllArgsConstructor
@Component
public class DailySubscriptionMailSenderJob implements Job {

	private final EmailService emailService;

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		final var emailId = context.getTrigger().getKey().getName();
		emailService.sendEmail(emailId, "Daily Quote", ShitQuoteUtility.get());
		log.info("Sent daily quote to: {}", emailId);
	}

}
