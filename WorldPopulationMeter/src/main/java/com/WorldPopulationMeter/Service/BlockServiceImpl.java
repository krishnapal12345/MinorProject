package com.WorldPopulationMeter.Service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.WorldPopulationMeter.Entity.BlockPopulationDetails;
import com.WorldPopulationMeter.Entity.State;
import com.WorldPopulationMeter.Repository.BlockRepository;
import com.WorldPopulationMeter.Repository.StateRepository;

import io.micrometer.core.annotation.Timed;
import jakarta.persistence.EntityNotFoundException;

@Service
public class BlockServiceImpl implements BlockService {

	@Autowired 
	private BlockRepository blockRepository;
	
	@Autowired
	private StateRepository stateRepository;
	
	@Timed(value="service.block.save" ,description="time taken to save block data")
	@Override
    public BlockPopulationDetails saveBlockData(BlockPopulationDetails details, String countryName, String stateName) {
        List<State> states = stateRepository.findByStateNameAndCountry_CountryName(stateName, countryName);
              if(states.isEmpty()) {  
        		throw new EntityNotFoundException("State not found: " + stateName + " in country: " + countryName);
              }
        State state=states.get(0);
        
        details.setState(state);
        return blockRepository.save(details);
    }
	@Timed(value="service.block.save" ,description="time taken to save block data")
	public List<BlockPopulationDetails> getAllData(String countryName,String stateName,int blockNumber){
		return blockRepository.findByCountryStateAndBlockNumber(countryName, stateName, blockNumber);
    }
}

	


	


