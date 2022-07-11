package com.bala.backend;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import com.twilio.Twilio;

@Configuration
public class TwilioInitializer {
	private final static Logger LOGGER = LoggerFactory.getLogger(TwilioInitializer.class);
	
	private final TwilioConfiguration twilioConfig;
	
	@Autowired
	public TwilioInitializer(TwilioConfiguration twilioConfig) {
		this.twilioConfig = twilioConfig;
		Twilio.init(this.twilioConfig.getAccountSid(), this.twilioConfig.getAuthToken());
		LOGGER.info("Twilio initialized successfully");
	}
}
