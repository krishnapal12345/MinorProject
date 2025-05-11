package com.WorldPopulationMeter.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.WorldPopulationMeter.Entity.Country;
import com.WorldPopulationMeter.Repository.CountryRepository;

import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Timer;

@Service
public class CountryService {

	@Autowired
	private CountryRepository countryRepository;
	
	@Autowired
	private MeterRegistry meterRegistry;
	
	public Country saveCountryData(String countryName) {
	 Timer timer=meterRegistry.timer("service.country.save.time");
	 return timer.record(() -> {
		 List<Country> countries = countryRepository.findByCountryName(countryName);
          if (countries.isEmpty()) {
     	   System.out.println("Saving new country: " + countryName);
           return countryRepository.save(new Country(countryName));    
     } else {
         return countries.get(0);
     }
		 
	 });
        
    }
}
