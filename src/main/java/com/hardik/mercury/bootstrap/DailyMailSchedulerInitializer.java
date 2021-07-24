package com.hardik.mercury.bootstrap;

import org.quartz.SchedulerException;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;

import com.hardik.mercury.quartz.DailyMailSubscriptionScheduler;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Configuration
@AllArgsConstructor
@Slf4j
public class DailyMailSchedulerInitializer implements ApplicationListener<ApplicationReadyEvent> {

	private final DailyMailSubscriptionScheduler dailyMailSubscriptionScheduler;

	@Override
	public void onApplicationEvent(ApplicationReadyEvent event) {
		try {
			dailyMailSubscriptionScheduler.start();
			log.info("Successfully initialized daily mail subscription scheduler");
		} catch (SchedulerException e) {
			log.error("Unable to initialize daily mail subscription scheduler: {}", e);
		}
	}

}
