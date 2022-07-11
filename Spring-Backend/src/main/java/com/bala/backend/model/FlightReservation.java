package com.bala.backend.model;

import java.time.LocalDate;
import java.time.LocalTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;



@Entity
@Table(name="flight_reservations")
public class FlightReservation {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="reservation_id")
	private int reservationId;
	@ManyToOne
	@JoinColumn(name="passenger_id")
	private Passenger passenger;
	@ManyToOne
	@JoinColumn(name="flight_number")
	private Flight flight;
	@Column(name="date_of_flying")
	private LocalDate flyingDate;
	@Column(name="time_of_flying")
	private LocalTime flyingTime;
	@Column(name="seat_type")
	private String seatType;
	@Column(name="no_of_seats")
	private int numOfSeats;
	@Column(name="total_cost")
	private double totalCost;
  @Column(name="confirmation_number")
  private String confirmationNumber;
  



	public FlightReservation() {
	}
	


	public FlightReservation(Passenger passenger, Flight flight, LocalDate flyingDate, LocalTime flyingTime,
			String seatType, int numOfSeats, double totalCost, String confirmationNumber) {
		super();
		this.passenger = passenger;
		this.flight = flight;
		this.flyingDate = flyingDate;
		this.flyingTime = flyingTime;
		this.seatType = seatType;
		this.numOfSeats = numOfSeats;
		this.totalCost = totalCost;
		this.confirmationNumber = confirmationNumber;
	}



	public int getReservationId() {
		return reservationId;
	}

	public void setReservationId(int reservationId) {
		this.reservationId = reservationId;
	}

	public Passenger getPassenger() {
		return passenger;
	}

	public void setPassenger(Passenger passenger) {
		this.passenger = passenger;
	}

	public Flight getFlight() {
		return flight;
	}

	public void setFlight(Flight flight) {
		this.flight = flight;
	}

	public LocalDate getFlyingDate() {
		return flyingDate;
	}

	public void setFlyingDate(LocalDate flyingDate) {
		this.flyingDate = flyingDate;
	}

	public LocalTime getFlyingTime() {
		return flyingTime;
	}

	public void setFlyingTime(LocalTime flyingTime) {
		this.flyingTime = flyingTime;
	}

	public String getSeatType() {
		return seatType;
	}

	public void setSeatType(String seatType) {
		this.seatType = seatType;
	}

	public int getNumOfSeats() {
		return numOfSeats;
	}

	public void setNumOfSeats(int numOfSeats) {
		this.numOfSeats = numOfSeats;
	}
	public double getTotalCost() {
		return totalCost;
	}

  public void setTotalCost(double totalCost) {
		this.totalCost = totalCost;
	}

	public String getConfirmationNumber() {
		return confirmationNumber;
	}

	public void setConfirmationNumber(String confirmationNumber) {
		this.confirmationNumber = confirmationNumber;
	}

	@Override
	public String toString() {
		return "FlightReservation [reservationId=" + reservationId + ", passenger=" + passenger + ", flight=" + flight
				+ ", flyingDate=" + flyingDate + ", flyingTime=" + flyingTime + ", seatType=" + seatType
				+ ", numOfSeats=" + numOfSeats + ", confirmationNumber=" + confirmationNumber + "]";
	}
	
	
	
}
