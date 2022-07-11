package com.bala.backend.model;


import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "flights")
public class Flight {
	@Id
	@Column(name="flight_number")
	private String flightNumber;
	@Column(name="airlines_name")
	private String airlinesName;
	@Column(name="departure_city")
	private String departureCity;
	@Column(name="destination_city")
	private String destinationCity;
	@Column(name="departure_date")
	private LocalDate departureDate;
	@Column(name="departure_time")
	private LocalTime departureTime;
	@Column(name="arrival_date")
	private LocalDate arrivalDate;
	@Column(name="arrival_time")
	private LocalTime arrivalTime;
	@Column(name="bc_seat_count")
	private int businessCount;
	@Column(name="bc_seat_price")
	private double businessPrice;
	@Column(name="ec_seat_count")
	private int economyCount;
	@Column(name="ec_seat_price")
	private double economyPrice;
	
	@OneToMany(mappedBy="flight", cascade = CascadeType.ALL , orphanRemoval = true)
	private List<FlightReservation> reservations;	
	
	public Flight() {
	}

	public Flight(String flightNumber) {
		this.flightNumber = flightNumber;
	}

	public Flight(String flightNumber, String airlinesName, String departureCity, String destinationCity,
			LocalDate departureDate, LocalTime departureTime, LocalDate arrivalDate, LocalTime arrivalTime,
			int businessCount, double businessPrice, int economyCount, double economyPrice) {
		super();
		this.flightNumber = flightNumber;
		this.airlinesName = airlinesName;
		this.departureCity = departureCity;
		this.destinationCity = destinationCity;
		this.departureDate = departureDate;
		this.departureTime = departureTime;
		this.arrivalDate = arrivalDate;
		this.arrivalTime = arrivalTime;
		this.businessCount = businessCount;
		this.businessPrice = businessPrice;
		this.economyCount = economyCount;
		this.economyPrice = economyPrice;
	}

	public String getFlightNumber() {
		return flightNumber;
	}

	public void setFlightNumber(String flightNumber) {
		this.flightNumber = flightNumber;
	}

	public String getAirlinesName() {
		return airlinesName;
	}

	public void setAirlinesName(String airlinesName) {
		this.airlinesName = airlinesName;
	}

	public String getDepartureCity() {
		return departureCity;
	}

	public void setDepartureCity(String departureCity) {
		this.departureCity = departureCity;
	}

	public String getDestinationCity() {
		return destinationCity;
	}

	public void setDestinationCity(String destinationCity) {
		this.destinationCity = destinationCity;
	}

	public LocalDate getDepartureDate() {
		return departureDate;
	}

	public void setDepartureDate(LocalDate departureDate) {
		this.departureDate = departureDate;
	}

	public LocalTime getDepartureTime() {
		return departureTime;
	}

	public void setDepartureTime(LocalTime departureTime) {
		this.departureTime = departureTime;
	}

	public LocalDate getArrivalDate() {
		return arrivalDate;
	}

	public void setArrivalDate(LocalDate arrivalDate) {
		this.arrivalDate = arrivalDate;
	}

	public LocalTime getArrivalTime() {
		return arrivalTime;
	}

	public void setArrivalTime(LocalTime arrivalTime) {
		this.arrivalTime = arrivalTime;
	}

	public int getBusinessCount() {
		return businessCount;
	}

	public void setBusinessCount(int businessCount) {
		this.businessCount = businessCount;
	}

	public double getBusinessPrice() {
		return businessPrice;
	}

	public void setBusinessPrice(double businessPrice) {
		this.businessPrice = businessPrice;
	}

	public int getEconomyCount() {
		return economyCount;
	}

	public void setEconomyCount(int economyCount) {
		this.economyCount = economyCount;
	}

	public double getEconomyPrice() {
		return economyPrice;
	}

	public void setEconomyPrice(double economyPrice) {
		this.economyPrice = economyPrice;
	}

	@Override
	public String toString() {
		return "Flight [flightNumber=" + flightNumber + ", airlinesName=" + airlinesName + ", departureCity="
				+ departureCity + ", destinationCity=" + destinationCity + ", departureDate=" + departureDate
				+ ", departureTime=" + departureTime + ", arrivalDate=" + arrivalDate + ", arrivalTime=" + arrivalTime
				+ ", businessCount=" + businessCount + ", businessPrice=" + businessPrice + ", economyCount="
				+ economyCount + ", economyPrice=" + economyPrice + "]";
	}
	
	
	
}
