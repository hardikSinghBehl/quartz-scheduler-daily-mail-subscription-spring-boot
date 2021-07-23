package com.hardik.mercury.mail;

import java.io.IOException;

import javax.mail.MessagingException;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.hardik.mercury.mail.properties.EmailConfigurationProperties;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
@EnableConfigurationProperties(EmailConfigurationProperties.class)
public class EmailService {

	private final JavaMailSender javaMailSender;
	private final EmailConfigurationProperties emailConfigurationProperties;

	public void sendEmail(String toMail, String subject, String messageBody) throws IOException, MessagingException {
		final var simpleMailMessage = new SimpleMailMessage();
		simpleMailMessage.setFrom(emailConfigurationProperties.getUsername());
		simpleMailMessage.setTo(toMail);
		simpleMailMessage.setSubject(subject);
		simpleMailMessage.setText(messageBody);
		javaMailSender.send(simpleMailMessage);
	}

}