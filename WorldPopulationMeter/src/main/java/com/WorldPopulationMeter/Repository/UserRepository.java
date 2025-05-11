package com.WorldPopulationMeter.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.WorldPopulationMeter.Entity.User;


@Repository
public interface UserRepository extends JpaRepository<User,Long> {

	User findByUsername(String username);
	
}
