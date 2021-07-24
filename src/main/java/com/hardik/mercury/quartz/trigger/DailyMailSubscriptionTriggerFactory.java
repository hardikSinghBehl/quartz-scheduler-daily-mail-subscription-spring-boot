package com.hardik.mercury.quartz.trigger;

import java.time.LocalDateTime;

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
		final var now = LocalDateTime.now();
		return TriggerBuilder.newTrigger().withIdentity(emailId)
				.forJob(dailySubscriptionMailSenderJobDetail.getJobDetail())
				.withSchedule(CronScheduleBuilder.cronSchedule("0 M H * * ?"
						.replace("M", String.valueOf(now.getMinute())).replace("H", String.valueOf(now.getHour()))))
				.usingJobData("email", emailId).build();
	}

}
