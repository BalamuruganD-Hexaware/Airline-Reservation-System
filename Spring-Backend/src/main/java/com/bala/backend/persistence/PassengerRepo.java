package com.bala.backend.persistence;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.bala.backend.model.Passenger;

@Repository
public interface PassengerRepo extends CrudRepository<Passenger, Integer> {
	Passenger findByEmailAndPassword(String email, String password);
}
