package com.WorldPopulationMeter.Dto;

import lombok.Data;

@Data
public class GraphGenderDto {

	private int maleCount;
	private int femaleCount;
	
	public GraphGenderDto(int maleCount,int femaleCount) {
		this.maleCount=maleCount;
		this.femaleCount=femaleCount;
	}
}
