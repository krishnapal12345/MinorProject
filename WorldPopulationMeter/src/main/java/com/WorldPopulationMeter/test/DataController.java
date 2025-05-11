package com.WorldPopulationMeter.test;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.WorldPopulationMeter.Entity.BlockPopulationDetails;
import com.WorldPopulationMeter.Entity.Country;
import com.WorldPopulationMeter.Entity.State;
import com.WorldPopulationMeter.Repository.BlockRepository;
import com.WorldPopulationMeter.Repository.CountryRepository;
import com.WorldPopulationMeter.Repository.StateRepository;

@RestController
@RequestMapping("/api")
public class DataController {

    @Autowired
    private CountryRepository countryRepository;

    @Autowired
    private StateRepository stateRepository;

    @Autowired
    private BlockRepository blockPopulationDetailsRepository;

    @Autowired
    private CountryStateService randomDataService; // Inject the service
    
   

    @PostMapping("/generate")
    public ResponseEntity<String> generateRandomData(@RequestParam int count) {
        try {
            // Generate multiple RandomDataDto entries
            List<RandomDataDto> randomDataList = randomDataService.generateRandomDataList(count);

            for (RandomDataDto randomData : randomDataList) {
                // Log to verify the data
                System.out.println("Received Data: " + randomData);

                // Find or create country
                List<Country> countries = countryRepository.findByCountryName(randomData.getCountry());
                Country country;
                if (countries.isEmpty()) {
                    country = new Country(randomData.getCountry());
                    countryRepository.save(country);
                    System.out.println("New country saved: " + country.getCountryName());
                } else {
                    country = countries.get(0);
                }

                // Find or create state
                List<State> states = stateRepository.findByStateNameAndCountry(randomData.getState(), country);
                State state;
                if (states.isEmpty()) {
                    state = new State(randomData.getState(), country);
                    stateRepository.save(state);
                    System.out.println("New state saved: " + state.getStateName());
                } else {
                    state = states.get(0);
                }

                // Create and save BlockPopulationDetails
                BlockPopulationDetails blockPopulationDetails = new BlockPopulationDetails(
                    randomData.getBlockNumber(),
                    randomData.getTotalPopulation(),
                    randomData.getMalePopulation(),
                    randomData.getFemalePopulation(),
                    randomData.getTotalEducated(),
                    randomData.getMaleEducated(),
                    randomData.getFemaleEducated(),
                    randomData.getAvgAge()
                );
                blockPopulationDetails.setState(state);

                blockPopulationDetailsRepository.save(blockPopulationDetails);
                System.out.println("Block population details saved for block number " + randomData.getBlockNumber());
            }

            return ResponseEntity.ok(count + " random data entries generated and saved successfully.");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            return ResponseEntity.status(500).body("Error generating data: " + e.getMessage());
        }
    }
}
