package com.bala.backend.helpers;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class Sms {
	private String receiverNumber;
	private String message;
	
	public Sms() {
	}
	
	public Sms(String receiverNumber, String message) {
		super();
		this.receiverNumber = receiverNumber;
		this.message = message;
	}
	
	public String getReceiverNumber() {
		return receiverNumber;
	}
	public void setReceiverNumber(String receiverNumber) {
		this.receiverNumber = "+91" + receiverNumber;
	}
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	

}
