package com.hardik.mercury.quartz.job.detail;

import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.springframework.stereotype.Component;

import com.hardik.mercury.quartz.job.DailySubscriptionMailSenderJob;

@Component
public class DailySubscriptionMailSenderJobDetail {

	private static JobDetail jobDetails = JobBuilder.newJob(DailySubscriptionMailSenderJob.class)
			.withIdentity("daily-subscription-mail-sender-job", "DEFAULT").storeDurably().build();

	public JobDetail getJobDetail() {
		return jobDetails;
	}

}