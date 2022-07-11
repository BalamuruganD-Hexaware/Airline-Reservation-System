package com.bala.backend.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bala.backend.helpers.Response;
import com.bala.backend.model.FlightReservation;
import com.bala.backend.model.Passenger;
import com.bala.backend.services.CancellationService;

@RestController
@RequestMapping("/cancellation")
public class CancellationController {
	@Autowired
	Response response;
	
	@Autowired
	CancellationService service;
  
	@PostMapping("/myReservations")
	public Response getMyReservations(@RequestBody Passenger passenger) {
		List<FlightReservation> reservations = this.service.showMyReservations(passenger);
		response.createResponse(reservations, 
				reservations.size() + "reservation(s) found", 
				"no reservations found");
		return response;
	}

	@PostMapping("/show")
	public Response showEligibleCancellations(@RequestBody Passenger p) {
		List<FlightReservation> reservations = this.service.showCurrentReservations(p);
		response.createResponse(reservations, reservations.size() + " reservation(s) found", "No reservation found");	
		return response;
	}

  @PostMapping("/showPast")
	public Response showPastReservations(@RequestBody Passenger p) {
		List<FlightReservation> reservations = this.service.showCurrentReservations(p);
		response.createResponse(reservations, reservations.size() + " reservation(s) found", "No reservation found");	
		return response;
	}
	
	@PostMapping("/cancel")
	public Response cancelReservation(@RequestBody FlightReservation reservation) {
		
		this.service.cancellation(reservation.getReservationId());
		response.setMessage("Cancellation Successful, Money will be refunded shortly!");
		response.setStatus(true);
		response.setResponseObject(null);
		return response;
	}
	
}
