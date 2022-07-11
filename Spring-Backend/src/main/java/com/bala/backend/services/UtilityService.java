package com.bala.backend.services;

import java.util.Optional;
import com.bala.backend.model.Flight;
import com.bala.backend.model.FlightReservation;
import com.bala.backend.model.Passenger;
import com.bala.backend.persistence.FlightRepo;
import com.bala.backend.persistence.FlightReservationRepo;
import com.bala.backend.persistence.PassengerRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class UtilityService {
  @Autowired
	PassengerRepo pRepo;

  @Autowired
	FlightReservationRepo frRepo;
    
	@Autowired
	FlightRepo fRepo;
  

  public FlightReservation getReservation(int id) {
		Optional<FlightReservation> p = frRepo.findById(id);
		if (p.isPresent())
			return p.get();
		else
			return null;
	}
  public Passenger getPassenger(int id) {
		Optional<Passenger> p = pRepo.findById(id);
		if (p.isPresent())
			return p.get();
		else
			return null;
	}
  public Flight getFlight(String id) {
    Optional<Flight> optionalflight = fRepo.findById(id);
    if(!optionalflight.isPresent()) return null;
    return optionalflight.get();
  }
	
}
