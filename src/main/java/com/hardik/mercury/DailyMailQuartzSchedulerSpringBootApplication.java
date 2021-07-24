package com.hardik.mercury;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class DailyMailQuartzSchedulerSpringBootApplication {

	public static void main(String[] args) {
		SpringApplication.run(DailyMailQuartzSchedulerSpringBootApplication.class, args);
	}

}
