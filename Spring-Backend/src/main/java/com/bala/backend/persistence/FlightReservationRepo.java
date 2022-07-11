package com.bala.backend.persistence;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.bala.backend.model.FlightReservation;
import com.bala.backend.model.Passenger;

@Repository
public interface FlightReservationRepo extends CrudRepository<FlightReservation, Integer> {

	List<FlightReservation> findByPassenger(Passenger p);
}
