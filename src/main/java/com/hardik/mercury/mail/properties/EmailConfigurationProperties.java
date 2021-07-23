package com.hardik.mercury.mail.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Data;

@Data
@ConfigurationProperties(prefix = "spring.mail")
public class EmailConfigurationProperties {

	private String username;
	private String password;

}