package com.hardik.mercury.quartz.trigger;

import org.quartz.CronScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.springframework.stereotype.Component;

import com.hardik.mercury.quartz.job.detail.DailySubscriptionMailSenderJobDetail;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class DailyMailSubscriptionTriggerFactory {

	private final DailySubscriptionMailSenderJobDetail dailySubscriptionMailSenderJobDetail;

	public Trigger generateTrigger(final String emailId) {
		return TriggerBuilder.newTrigger().withIdentity(emailId)
				.forJob(dailySubscriptionMailSenderJobDetail.getJobDetail())
				.withSchedule(CronScheduleBuilder.cronSchedule("0 0 12 * * ?")).usingJobData("email", emailId).build();
	}

}
