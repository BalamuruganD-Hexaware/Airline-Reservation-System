package com.bala.backend;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties("razorpay")
public class RazorpayConfig {
	private String keyId;
	private String keySecret;
	
	public RazorpayConfig() {}
	
	public String getKeyId() {
		return keyId;
	}
	public void setKeyId(String keyId) {
		this.keyId = keyId;
	}
	public String getKeySecret() {
		return keySecret;
	}
	public void setKeySecret(String keySecret) {
		this.keySecret = keySecret;
	}
	
	
}
