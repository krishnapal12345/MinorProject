package com.WorldPopulationMeter.Service;

import com.WorldPopulationMeter.Entity.User;

public interface UserService {

	User findByUsername(String username);
	User save(User user);
	
}
