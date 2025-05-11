package com.WorldPopulationMeter.Controller;
import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.WorldPopulationMeter.Dto.EduGraphDto;
import com.WorldPopulationMeter.Dto.GraphGenderDto;
import com.WorldPopulationMeter.Entity.BlockPopulationDetails;
import com.WorldPopulationMeter.Entity.State;
import  com.WorldPopulationMeter.Service.BlockService;
import com.WorldPopulationMeter.Service.BlockServiceImpl;
import com.WorldPopulationMeter.Service.ContactFormService;
import com.WorldPopulationMeter.Service.EducationalStat;
import com.WorldPopulationMeter.Service.GenderRatioStat;
import com.WorldPopulationMeter.Service.UserService;

import io.micrometer.core.instrument.MeterRegistry;




@RestController
@RequestMapping("/api")
public class UserController {

	@Autowired
	UserDetailsService userDetailsService;
	
	@Autowired
	private EducationalStat educationalStat;
	
	@Autowired
	private GenderRatioStat genderStat;
	
	@Autowired
	private MeterRegistry meterRegistry;
	
	private static  Logger logger=LoggerFactory.getLogger(UserController.class);
	

	@GetMapping("/user-page")
	public String userPage (Model model, Principal principal) {
		UserDetails userDetails = userDetailsService.loadUserByUsername(principal.getName());
		model.addAttribute("user", userDetails);
		return "user";
	}
	
	@GetMapping("/get-edu-graph")
	@ResponseBody
	public List<EduGraphDto> getEduData(@RequestParam("countryName") String countryName,@RequestParam("stateName") String stateName,@RequestParam("blockNumber")int blockNumber){
		System.out.println("country:"+countryName);
		logger.info("calling get-edu-graph for country: {}, state: {}, block number: {}", countryName, stateName, blockNumber);
		List<EduGraphDto> educationDetails=meterRegistry.timer("api.fetch.get-eduData").record(()->{
			return educationalStat.getEduData(countryName, stateName, blockNumber);
		});
		return educationDetails;
	}
	@GetMapping("/get-Data")
	@ResponseBody
	public ResponseEntity<List<BlockPopulationDetails>> getGraphData(
	        @RequestParam("countryName") String countryName,
	        @RequestParam("stateName") String stateName,
	        @RequestParam("blockNumber") int blockNumber) {
	    
		
	    logger.info("Fetching block population details for country: {}, state: {}, block number: {}", 
	                countryName, stateName, blockNumber);
	    	 // Fetch data using the educationalStat service
		    List<BlockPopulationDetails> blockDetails =meterRegistry.timer("api.fetch.get-data").record(()->{
		    	return educationalStat.getBlockPopulationDetails(countryName, stateName, blockNumber);
		    });
		    
		    // Check if data exists
		    if (blockDetails.isEmpty()) {
		        logger.warn("No block population details found for country: {}, state: {}, block number: {}", 
		                    countryName, stateName, blockNumber);
		        return ResponseEntity.notFound().build();  // Return 404 status if no data is found
		    }
		    
		    return ResponseEntity.ok(blockDetails);  // Return 200 status with data
	}

	@GetMapping("/get-Gender-graph")
	@ResponseBody
    public List<GraphGenderDto> getGenderData(@RequestParam("countryName") String countryName,@RequestParam("stateName") String stateName,@RequestParam("blockNumber")int blockNumber){
		logger.info("calling get-Gender-graph for country: {}, state: {}, block number: {}", countryName, stateName, blockNumber);
		List<GraphGenderDto> genderDetails=meterRegistry.timer("api.fetch.get-genData").record(()->{
			return genderStat.getGenderData(countryName, stateName, blockNumber);
		});
		return genderDetails;
	}
}
	

	
	

