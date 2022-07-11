package com.bala.backend.controllers;

import java.util.HashMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.bala.backend.helpers.Response;
import com.bala.backend.model.FlightReservation;
import com.bala.backend.model.RazorpayPayment;
import com.bala.backend.services.ReservationService;

@RestController
@RequestMapping("/reservation")
public class ReservationController {

	@Autowired
	ReservationService service;

	@Autowired
	Response response;

	@PostMapping("/getTotalCost")
	public Response getTotalCost(@RequestBody HashMap<String, String> payload) {
		Double price = this.service.getPrice(payload.get("fID"), payload.get("seatType"));
		int numOfSeats = Integer.parseInt(payload.get("numOfSeats"));
		response.setMessage("Success");
		response.setStatus(true);
		response.setResponseObject(price * numOfSeats);
		return response;
	}

	@PostMapping("/order")
	public Response postOrder(@RequestBody HashMap<String, Integer> data) {
		String orderId = this.service.makeOrder(data.get("amount"));
		response.createResponse(orderId, "Order created", "Order not created");
		return response;
	}
	
	@PostMapping("/reserveSeats")
	public Response reserveSeats(@RequestBody HashMap<String, String> payload) {
		FlightReservation reservation = this.service.makeReservation(payload);
		this.service.savePaymentDetails(new RazorpayPayment(
				payload.get("razorpayPaymentId"),
				payload.get("razorpayOrderId"),
				payload.get("razorpaySignature"),
				reservation
				));
		response.createResponse(reservation, "Success", "Failure");
		return response;
	}
}
