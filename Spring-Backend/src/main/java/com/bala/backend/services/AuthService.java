package com.bala.backend.services;


import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.bala.backend.helpers.Sms;
import com.bala.backend.model.Passenger;
import com.bala.backend.persistence.PassengerRepo;

@Service
public class AuthService {

	@Autowired
	PassengerRepo repo;

	@Autowired
	MessagingService messenger;
	
	@Autowired
	Sms message;
	
	public Passenger passengerLogin(HashMap<String, String> pas) {
		return repo.findByEmailAndPassword(pas.get("email"), pas.get("password")); 
	}
	
	public Passenger registerPassenger(Passenger passenger) throws DataIntegrityViolationException {
		Passenger passenger2 = repo.save(passenger);
		message.setReceiverNumber("9940968020");
		message.setMessage(
				"Welcome " + passenger2.getFirstName() + ",\n"
				+ "Reserve your first flight ticket now!");
		
		messenger.sendMessage(message);
		return passenger2;
	}
}
