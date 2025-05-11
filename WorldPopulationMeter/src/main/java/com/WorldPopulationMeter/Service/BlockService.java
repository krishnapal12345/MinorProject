package com.WorldPopulationMeter.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.WorldPopulationMeter.Entity.BlockPopulationDetails;
import com.WorldPopulationMeter.Entity.State;

@Service
public interface BlockService {

	BlockPopulationDetails saveBlockData(BlockPopulationDetails details,String countryName,String stateName);

	List<BlockPopulationDetails> getAllData(String countryName,String stateName,int blockNumber);
	
	
}
