package com.hardik.mercury.quartz;

import org.quartz.ObjectAlreadyExistsException;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import com.hardik.mercury.quartz.configuration.QuartzSchedulerConfiguration;
import com.hardik.mercury.quartz.job.detail.DailySubscriptionMailSenderJobDetail;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@AllArgsConstructor
@Slf4j
public class DailyMailSubscriptionScheduler {

	private final Scheduler scheduler;
	private final DailySubscriptionMailSenderJobDetail dailySubscriptionMailSenderJobDetail;
	private final QuartzSchedulerConfiguration quartzSchedulerConfiguration;
	private final ApplicationContext applicationContext;

	public void start() throws SchedulerException {
		if (!this.scheduler.isStarted()) {
			this.scheduler.start();
			this.quartzSchedulerConfiguration.setApplicationContext(applicationContext);
			this.scheduler.setJobFactory(quartzSchedulerConfiguration.getFactory());
			this.scheduler.addJob(dailySubscriptionMailSenderJobDetail.getJobDetail(), false);
		}
	}

	public void addTriggerInDailyMailSubscriptionService(Trigger trigger) throws SchedulerException {
		try {
			this.scheduler.scheduleJob(dailySubscriptionMailSenderJobDetail.getJobDetail(), trigger);
		} catch (ObjectAlreadyExistsException exception) {
			log.error("Daily mail sender Trigger Already Added!");
		}
	}

}