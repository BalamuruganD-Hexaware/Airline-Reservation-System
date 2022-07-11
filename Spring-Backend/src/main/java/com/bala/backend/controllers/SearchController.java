package com.bala.backend.controllers;
import java.util.List;
import com.bala.backend.helpers.Response;
import com.bala.backend.model.Flight;
import com.bala.backend.services.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/search")
public class SearchController {
  @Autowired
	SearchService service;

	@Autowired
	Response response;

  @GetMapping("/getAllFlights")
	public Response getAllFlights() {
		List<Flight> flights = this.service.getAllFlights();
		response.createResponse(flights, 
				flights.size() + " flight(s) available", 
				"No available flights");
		return response;
	}
	
	@PostMapping("/showAvailableFlights")
	public Response showAvailableFlights(@RequestBody Flight flight) {
		List<Flight> flights = this.service.getAvailableFlights(flight);
		response.createResponse(flights, 
							flights.size() + " flight(s) available", 
							"No available flights");
		return response;
	}
}
