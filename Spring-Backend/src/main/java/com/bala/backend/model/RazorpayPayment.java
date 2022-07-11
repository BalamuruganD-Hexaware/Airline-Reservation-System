package com.bala.backend.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="razorpay_payment")
public class RazorpayPayment {
	@Id
	@Column(name="razorpay_payment_id")
	private String razorpayPaymentId;
	@Column(name="razorpay_order_id")
	private String razorpayOrderId;
	@Column(name="razorpay_signature")
	private String razorpaySignature;
	@OneToOne
	@JoinColumn(name="reservation_id")
	private FlightReservation reservation;
	
	public RazorpayPayment() {
	}
	
	public RazorpayPayment(String razorpayPaymentId, String razorpayOrderId, String razorpaySignature,
			FlightReservation reservation) {
		super();
		this.razorpayPaymentId = razorpayPaymentId;
		this.razorpayOrderId = razorpayOrderId;
		this.razorpaySignature = razorpaySignature;
		this.reservation = reservation;
	}

	public String getRazorpayPaymentId() {
		return razorpayPaymentId;
	}

	public void setRazorpayPaymentId(String razorpayPaymentId) {
		this.razorpayPaymentId = razorpayPaymentId;
	}

	public String getRazorpayOrderId() {
		return razorpayOrderId;
	}

	public void setRazorpayOrderId(String razorpayOrderId) {
		this.razorpayOrderId = razorpayOrderId;
	}

	public String getRazorpaySignature() {
		return razorpaySignature;
	}

	public void setRazorpaySignature(String razorpaySignature) {
		this.razorpaySignature = razorpaySignature;
	}

	public FlightReservation getReservationId() {
		return reservation;
	}

	public void setReservationId(FlightReservation reservation) {
		this.reservation = reservation;
	}

	@Override
	public String toString() {
		return "RazorpayPayment [razorpayPaymentId=" + razorpayPaymentId + ", razorpayOrderId=" + razorpayOrderId
				+ ", razorpaySignature=" + razorpaySignature + ", reservation=" + reservation + "]";
	}
	
	
	
}
