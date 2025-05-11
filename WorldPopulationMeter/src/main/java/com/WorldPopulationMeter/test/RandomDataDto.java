package com.WorldPopulationMeter.test;

import lombok.Data;

@Data
public class RandomDataDto {

    private String country;
    private String state;
    private int blockNumber;
    private int totalPopulation;
    private int malePopulation;
    private int femalePopulation;
    private int totalEducated;
    private int maleEducated;
    private int femaleEducated;
    private int avgAge;

    // Correct constructor
    public RandomDataDto(String country, String state, int blockNumber, int totalPopulation, 
                         int malePopulation, int femalePopulation, int totalEducated, 
                         int maleEducated, int femaleEducated, int avgAge) {
        this.country = country;
        this.state = state;
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
