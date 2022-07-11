package com.bala.backend.helpers;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class Response {
	private boolean status;
	private String message;
	private Object responseObject;
	
	public Response() {
	}
	
	public Response(boolean status, String message, Object responseObject) {
		super();
		this.status = status;
		this.message = message;
		this.responseObject = responseObject;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getResponseObject() {
		return responseObject;
	}

	public void setResponseObject(Object responseObject) {
		this.responseObject = responseObject;
	}

	public void createResponse(Object obj, String successMessage, String failureMessage) {
		if (obj == null) {
			setMessage(failureMessage);
			setStatus(false);
		} else {
			setMessage(successMessage);
			setStatus(true);
		}
		setResponseObject(obj);
	}
	
}
