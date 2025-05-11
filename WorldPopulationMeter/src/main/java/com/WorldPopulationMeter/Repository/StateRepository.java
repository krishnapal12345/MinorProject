package com.WorldPopulationMeter.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.WorldPopulationMeter.Entity.Country;
import com.WorldPopulationMeter.Entity.State;



@Repository
public interface StateRepository extends JpaRepository<State,Integer>{
	@Query("SELECT s FROM State s WHERE s.stateName = :stateName")
    List<State> findStatesByName(@Param("stateName") String stateName);

	List<State> findByStateNameAndCountry(String stateName, Country country);
    List<State> findByStateNameAndCountry_CountryName(String stateName, String countryName);

 }
