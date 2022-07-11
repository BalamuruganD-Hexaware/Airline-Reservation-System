package com.bala.backend.persistence;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.bala.backend.model.Flight;

@Repository
public interface FlightRepo extends JpaRepository<Flight, String> {
	List<Flight> findByDepartureCityAndDestinationCity(String departureCity, String destinationCity);
	List<Flight> findByDepartureCityAndDestinationCityAndDepartureDate(String departureCity, String destinationCity, LocalDate fromDate);
  List<Flight> findByDepartureCityAndDestinationCityAndDepartureDateAndDepartureTime(
    String departureCity, 
    String destinationCity, 
    LocalDate fromDate, 
    LocalTime fromTime);
}
