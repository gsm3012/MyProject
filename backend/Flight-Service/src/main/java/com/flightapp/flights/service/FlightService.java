package com.flightapp.flights.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flightapp.flights.model.Flight;
import com.flightapp.flights.repository.FlightRepository;

@Service
public class FlightService 
{
	@Autowired
	private FlightRepository flightRepo;
	
	
//	Create Flight
	
	public void createNewFlight(Flight flight)
	{
		this.flightRepo.save(flight);
	}
	

//	Get All Flight
	
	public List<Flight> getAllFlight()
	{
		return this.flightRepo.findAll();
	}
	
	
//	get Single Flight
	
	public Flight getSingleFlight(int id)
	{
		return this.flightRepo.findById(id).orElseThrow(()-> new NoSuchElementException("Flight is not present with given Id"));
	}
	

//	update flight
	
	public void updateFlight(Flight flight)
	{
		List<Flight> flights = this.flightRepo.findAll();
		
		List<Flight> updatedFlights = flights.stream().map(e->{
			if(e.getFlightId()==flight.getFlightId())
			{
				e.setArrival(flight.getArrival());
				e.setBusinessClassSeat(flight.getBusinessClassSeat());
				e.setNonBusinessClassSeat(flight.getNonBusinessClassSeat());
				e.setDeparture(flight.getArrival());
				e.setFromPlace(flight.getFromPlace());
				e.setToPlace(flight.getToPlace());
				e.setMeal(flight.getMeal());
				e.setScheduleDays(flight.getScheduleDays());
				e.setTicketCost(flight.getTicketCost());
			}
			return e;
		}).collect(Collectors.toList());
		
		this.flightRepo.saveAll(updatedFlights);
	}

	
//	Delete flight
	
	public void delteFlight(int id)
	{
		this.flightRepo.deleteById(id);
	}


}
