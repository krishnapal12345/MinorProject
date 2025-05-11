package com.WorldPopulationMeter.Entity;



import java.util.List;
import java.util.Optional;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name="BlockPopulationDetails")
@Data
public class BlockPopulationDetails {
	

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private int blockNumber;
	private int totalPopulation;
	private int malePopulation;
	private int femalePopulation;
	private int totalEducated;
	private int maleEducated;
	private int femaleEducated;
	private int avgAge;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "state_id", referencedColumnName = "id")
	@JsonBackReference
	private State state;
	
	
    public BlockPopulationDetails(){
		
	}
	
	
	public BlockPopulationDetails(int blockNumber, int totalPopulation,
			int malePopulation, int femalePopulation, int totalEducated, int maleEducated, int femaleEducated,
			int avgAge) {
		
		this.blockNumber = blockNumber;
		this.totalPopulation = totalPopulation;
		this.malePopulation = malePopulation;
		this.femalePopulation = femalePopulation;
		this.totalEducated = totalEducated;
		this.maleEducated = maleEducated;
		this.femaleEducated = femaleEducated;
		this.avgAge = avgAge;
	}


	
}
