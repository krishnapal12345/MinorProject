package com.WorldPopulationMeter.Entity;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name="countries")
@Data
public class Country {



	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String countryName;
	
	@OneToMany(mappedBy="country" ,cascade=CascadeType.ALL,fetch=FetchType.LAZY)
	@JsonIgnore
	private List<State> states;

	public Country() {
		
	}
	public Country(String countryName) {
		// TODO Auto-generated constructor stub
		this.countryName=countryName;
		this.states=new ArrayList<>();
	}
}
