package com.bala.backend.services;

import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bala.backend.RazorpayConfig;
import com.bala.backend.helpers.Sms;
import com.bala.backend.model.Flight;
import com.bala.backend.model.FlightReservation;
import com.bala.backend.model.Passenger;
import com.bala.backend.model.RazorpayPayment;
import com.bala.backend.persistence.FlightReservationRepo;
import com.bala.backend.persistence.PassengerRepo;
import com.bala.backend.persistence.RazorpayPaymentRepo;
import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;

@Service
public class ReservationService {

	private RazorpayClient client;
	private final RazorpayConfig config;
	
  @Autowired
  UtilityService utility;

	@Autowired
	FlightReservationRepo frRepo;
	
	@Autowired
	PassengerRepo pRepo;
	
	@Autowired
	RazorpayPaymentRepo rpRepo;
	
	@Autowired
	MessagingService messenger;
	
	@Autowired
	Sms message;
	
	public ReservationService(RazorpayConfig config) throws RazorpayException {
		this.config = config;
		this.client = new RazorpayClient(this.config.getKeyId(), this.config.getKeySecret());
	}
		
	public double getPrice(String id, String type) {
		Flight flight = this.utility.getFlight(id);
		if (type.equals("Business")) return flight.getBusinessPrice();
		return flight.getEconomyPrice();
	}
	
	public String getConfirmationNumber(Flight flight) {
		int random = (int) Math.random() * (5000 - 1 + 1) + 1;
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMddYYYY");
		return formatter.format(flight.getDepartureDate()) 
				+ flight.getDepartureTime().toString().replace(":", "")
				+ flight.getFlightNumber()
				+ random;
	}

	public String makeOrder(int amount) {
		try {
			  JSONObject orderRequest = new JSONObject();
			  orderRequest.put("amount", amount*100); // amount in the smallest currency unit
			  orderRequest.put("currency", "INR");
			  orderRequest.put("receipt", "order_rcptid_11");

			  Order order = this.client.Orders.create(orderRequest);
			  return order.get("id");
			} catch (RazorpayException e) {
			  System.out.println(e.getMessage());
			  return null;
			}
	}
	
	public void savePaymentDetails(RazorpayPayment payment) {
		rpRepo.save(payment);
	}

	public FlightReservation makeReservation(HashMap<String, String> payload) {
		Flight flight = this.utility.getFlight(payload.get("flightID"));
		String confirmationNumber = this.getConfirmationNumber(flight);
		int pID = Integer.parseInt(payload.get("pID"));
		Passenger passenger = this.utility.getPassenger(pID);
		FlightReservation reservation = new FlightReservation(
				passenger,
				flight,
				flight.getDepartureDate(),
				flight.getDepartureTime(),
				payload.get("seatType"),
				Integer.parseInt(payload.get("numOfSeats")),
				Double.parseDouble(payload.get("totalCost")),
				confirmationNumber 
		);
				
		this.message.setReceiverNumber("9940968020");
		this.message.setMessage(
				"Hey " + passenger.getFirstName() + "\n"
				+ "Your flight reservation is successful.\n" 
				+ "Summary:\n" 
				+ flight.getDepartureCity() + " to " + flight.getDestinationCity() + "\n"
				+ flight.getDepartureDate() + " to " + flight.getArrivalDate() + "\n"
				+ "Number of seats: " + reservation.getNumOfSeats() + "\n"
				+ "Seat type: " + reservation.getSeatType() + "\n"
				+ "Total Cost: " + reservation.getTotalCost() + "\n"
				+ "Confirmation number: " + reservation.getConfirmationNumber()
				);
		messenger.sendMessage(this.message);
		return frRepo.save(reservation);
	}
	

}
