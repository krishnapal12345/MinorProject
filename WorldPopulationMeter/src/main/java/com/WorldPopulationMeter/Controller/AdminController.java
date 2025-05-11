package com.WorldPopulationMeter.Controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.WorldPopulationMeter.Entity.BlockPopulationDetails;
import com.WorldPopulationMeter.Entity.Country;
import com.WorldPopulationMeter.Entity.State;
import com.WorldPopulationMeter.Repository.CountryRepository;
import com.WorldPopulationMeter.Repository.StateRepository;
import com.WorldPopulationMeter.Service.BlockService;
import com.WorldPopulationMeter.Service.CountryService;
import com.WorldPopulationMeter.Service.MonitoringService;
import com.WorldPopulationMeter.Service.StateService;

import io.micrometer.core.annotation.Timed;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Timer;
import jakarta.persistence.EntityNotFoundException;

@RestController
@RequestMapping("/api")
public class AdminController {

    @Autowired
    UserDetailsService userDetailsService;

    @Autowired
    private BlockService blockService;
    
    @Autowired
    private CountryRepository countryRepository;
    
    @Autowired
    private CountryService countryService;
    
    @Autowired
    private StateService stateService;

    @Autowired
    private StateRepository stateRepository; 
    
    @Autowired
    private MonitoringService monitoringService;
    
    @Autowired
    private MeterRegistry meterRegistry;
    

    @GetMapping("/admin-page")
    public String adminPage(Model model, Principal principal) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(principal.getName());
        model.addAttribute("user", userDetails);
        model.addAttribute("BlockPopulationDetails", new BlockPopulationDetails());
        return "admin";
    }

    @Timed(value="api.submit.country.state.block.timer",description="time taken to submit data",histogram=true)
    @PostMapping("/submit-data")
    public ResponseEntity<String> SaveData(@RequestBody BlockPopulationDetails blockPopulationDetails,@RequestParam("countryName")String countryName,@RequestParam("stateName")String stateName){
    	try {
    	
            
    	    meterRegistry.timer("api.save.data.timer")
    	    .record(()->{
    	    	System.out.println("country-"+ countryName);
        		System.out.println("state-"+stateName);
        		countryService.saveCountryData(countryName);
        		stateService.saveStateData(stateName, countryName);
        		
        		blockService.saveBlockData(blockPopulationDetails, countryName, stateName);
        		
        		monitoringService.recordSubmission(countryName, stateName);
        		return ResponseEntity.ok("Data submitted succesfully");
    	    });
    	  return ResponseEntity.ok("Data submitted succesfully");
    		
    	}catch(EntityNotFoundException e) {
    		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Data submission failed: " + e.getMessage());
    	}
    }
}
