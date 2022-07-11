package com.bala.backend.persistence;

import org.springframework.data.repository.CrudRepository;

import com.bala.backend.model.FlightReservation;
import com.bala.backend.model.RazorpayPayment;

public interface RazorpayPaymentRepo extends CrudRepository<RazorpayPayment, String> {
	RazorpayPayment findByReservation(FlightReservation reservation);
}
