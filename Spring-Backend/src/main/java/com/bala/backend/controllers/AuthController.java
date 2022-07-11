package com.bala.backend.controllers;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bala.backend.helpers.Response;
import com.bala.backend.model.Passenger;

import com.bala.backend.services.AuthService;

@RestController
@RequestMapping("/auth")
public class AuthController {
	@Autowired
	AuthService service;
	
	@Autowired
	Response response;
	
	
	@PostMapping(path= "/login", 
			consumes = {MediaType.APPLICATION_JSON_VALUE},
	        produces = {MediaType.APPLICATION_JSON_VALUE})
	public Response login(@RequestBody HashMap<String, String> pas) {
		Passenger p = this.service.passengerLogin(pas);
		response.createResponse(p, "Authentication Success", "Authentication Failed");
		return response; 
	}
	
	
	
	@PostMapping(path= "/register", 
			consumes = {MediaType.APPLICATION_JSON_VALUE},
	        produces = {MediaType.APPLICATION_JSON_VALUE})
	public Response register(@RequestBody Passenger passenger) {
		String failureMessage = "";
		Passenger p = null;
		try {
			p = this.service.registerPassenger(passenger);			
		} catch(DataIntegrityViolationException s) {
			failureMessage = "Email or phone number already exists! Registration failed";
		}
		response.createResponse(p, "Registration Success", failureMessage);
		return response;
	}
}
