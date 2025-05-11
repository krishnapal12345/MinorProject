package com.WorldPopulationMeter.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.WorldPopulationMeter.Entity.Country;
import com.WorldPopulationMeter.Entity.State;
import com.WorldPopulationMeter.Repository.CountryRepository;
import com.WorldPopulationMeter.Repository.StateRepository;

import io.micrometer.core.annotation.Timed;
import jakarta.persistence.EntityNotFoundException;

@Service
public class StateService {

	@Autowired
	private StateRepository stateRepository;
	
	@Autowired
	private CountryRepository countryRepository;
	
	@Timed(value="service.state.save",description="time taken to save state")
	public State saveStateData(String stateName, String countryName) {
		
		List<Country> countries = countryRepository.findByCountryName(countryName);//to handle duplicate entries
        if (countries.isEmpty()) {
            throw new EntityNotFoundException("Country not found: " + countryName);
        }
        Country country = countries.get(0);// to get first country if more then one country found
        
        List<State> states = stateRepository.findByStateNameAndCountry(stateName, country);
        if (states.isEmpty()) {
        	System.out.println("Saving new state: " + stateName);
            return stateRepository.save(new State(stateName, country));
        } else {
            
            return states.get(0);//to get first state if more than one state found
        }
    }
}
