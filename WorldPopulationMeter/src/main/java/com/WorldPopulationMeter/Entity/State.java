package com.WorldPopulationMeter.Entity;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name="states")
@Data
public class State {
	


	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String stateName;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "country_id", referencedColumnName = "id")
	private Country country;
	
	@OneToMany(mappedBy="state", cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	@JsonManagedReference
	private List<BlockPopulationDetails> blockPopulationDetails=new ArrayList<>();
	
	public State() {
		
	}
	public State(String stateName, Country country) {
		// TODO Auto-generated constructor stub
		this.stateName=stateName;
		this.country=country;
		this.blockPopulationDetails=new ArrayList<>();
	}

	
}

