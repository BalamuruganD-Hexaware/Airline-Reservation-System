package com.bala.backend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bala.backend.TwilioConfiguration;
import com.bala.backend.helpers.Sms;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.rest.api.v2010.account.MessageCreator;
import com.twilio.rest.api.v2010.account.ValidationRequest;
import com.twilio.type.PhoneNumber;

@Service
public class MessagingService {

	@Autowired
	private TwilioConfiguration config;
	
	

	public ValidationRequest verifyNumber(String pNumber, String friendlyName) {
		PhoneNumber phoneNumber = new PhoneNumber("+91" + pNumber);
		ValidationRequest validation = ValidationRequest.creator(phoneNumber)
				.setFriendlyName(friendlyName).create();
		return validation;
	}

	public void sendMessage(Sms message) {
		PhoneNumber to = new PhoneNumber(message.getReceiverNumber());
		PhoneNumber from = new PhoneNumber(config.getTrialNumber());
		MessageCreator creator = Message.creator(to, from, message.getMessage());
		creator.create();
	}
}
