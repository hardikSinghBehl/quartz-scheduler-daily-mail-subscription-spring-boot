package com.hardik.mercury.quartz;

import org.quartz.ObjectAlreadyExistsException;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerKey;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import com.hardik.mercury.exception.EmailAlreadyRegisteredException;
import com.hardik.mercury.exception.GenericServerException;
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

	public void addTriggerInDailyMailSubscriptionService(Trigger trigger) {
		try {
			this.scheduler.scheduleJob(dailySubscriptionMailSenderJobDetail.getJobDetail(), trigger);
			log.info("Successfully scheduled trigger with identity: {}", trigger.getKey());
		} catch (ObjectAlreadyExistsException exception) {
			log.error("Daily mail sender Trigger Already Added!");
			throw new EmailAlreadyRegisteredException();
		} catch (SchedulerException e) {
			log.error("Unable to add trigger {}", e);
		}
	}

	public void removeTrigger(final String email) {
		try {
			this.scheduler.unscheduleJob(new TriggerKey(email));
		} catch (SchedulerException e) {
			log.error("Unable to unschedule email from daily mail subscription service: {}", e);
			throw new GenericServerException();
		}
	}

	public Boolean triggerWithEmailScheduled(final String emailId) {
		try {
			return this.scheduler.checkExists(new TriggerKey(emailId));
		} catch (SchedulerException e) {
			log.error("Unable to check for trigger existence: {}", e);
			throw new GenericServerException();
		}
	}

}