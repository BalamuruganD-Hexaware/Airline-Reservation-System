package com.bala.backend.services;

import java.util.List;

import com.bala.backend.model.Flight;
import com.bala.backend.persistence.FlightRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SearchService {
  @Autowired
	FlightRepo fRepo;

  
  public List<Flight> getAllFlights() {
    return fRepo.findAll();
  }

  public List<Flight> getAvailableFlights(Flight flight) {
    if (flight.getDepartureDate() == null && flight.getDepartureTime() == null) {
      return fRepo.findByDepartureCityAndDestinationCity(flight.getDepartureCity(),
      flight.getDestinationCity());
    } 

    else if (flight.getDepartureDate() != null && flight.getDepartureTime() == null) {
      return fRepo.findByDepartureCityAndDestinationCityAndDepartureDate(flight.getDepartureCity(),
      flight.getDestinationCity(),
      flight.getDepartureDate());
    }

		return fRepo.findByDepartureCityAndDestinationCityAndDepartureDateAndDepartureTime(
				flight.getDepartureCity(),
				flight.getDestinationCity(),
        flight.getDepartureDate(),
        flight.getDepartureTime()
				);
	}
}
