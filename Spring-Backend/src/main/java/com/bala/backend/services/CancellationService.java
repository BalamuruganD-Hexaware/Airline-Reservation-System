package com.bala.backend.services;

import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bala.backend.RazorpayConfig;
import com.bala.backend.helpers.Sms;
import com.bala.backend.model.FlightReservation;
import com.bala.backend.model.Passenger;
import com.bala.backend.model.RazorpayPayment;
import com.bala.backend.persistence.FlightReservationRepo;
import com.bala.backend.persistence.RazorpayPaymentRepo;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import com.razorpay.Refund;

@Service
public class CancellationService {
	
	private RazorpayClient client;
	private final RazorpayConfig config;
	
	@Autowired
	FlightReservationRepo frRepo;

	@Autowired
	RazorpayPaymentRepo rpRepo;

	@Autowired
	MessagingService messenger;

  @Autowired
  UtilityService utility;

	@Autowired
	Sms message;
	
	public CancellationService(RazorpayConfig config) throws RazorpayException {
		this.config = config;
		this.client = new RazorpayClient(this.config.getKeyId(), this.config.getKeySecret());
	}
	
  public List<FlightReservation> showMyReservations(Passenger p) {
		return frRepo.findByPassenger(p);
	}
  
	public List<FlightReservation> showCurrentReservations(Passenger p) {
		List<FlightReservation> reservations = frRepo.findByPassenger(p);
		reservations.removeIf((reservation) -> LocalDate.now().isAfter(reservation.getFlyingDate()));
		return reservations;
	}

  public List<FlightReservation> showPastReservations(Passenger p) {
		List<FlightReservation> reservations = frRepo.findByPassenger(p);
		reservations.removeIf((reservation) -> reservation.getFlyingDate().isAfter(LocalDate.now()));
		return reservations;
	}

	public void cancellation(int reservationId) {
		FlightReservation reservation = this.utility.getReservation(reservationId);
		System.out.println(reservation);
		RazorpayPayment payment = rpRepo.findByReservation(reservation);
		System.out.println(payment);
		Passenger p = this.utility.getPassenger(reservation.getPassenger().getpID());
		Refund refund = null;
		try {
			refund = client.Payments.refund(payment.getRazorpayPaymentId());
		} catch (RazorpayException e) {
			System.out.println(e.getMessage());
		}
		rpRepo.delete(payment);
		frRepo.delete(reservation);
		message.setReceiverNumber("9940968020");
		message.setMessage("Hello " + p.getFirstName() + ",\n" + "Your flight reservation on " + reservation.getFlyingDate()
				+ " is cancelled successfully.\n" + "Your money will be refunded in 5-7 working days!.\n"
        + "Amount: " + (int) refund.get("amount") / 100 + " " + refund.get("currency") + "\n"
        + "Reservation Confirmation number: " + reservation.getConfirmationNumber() + "\n");
		messenger.sendMessage(message);
	}
}