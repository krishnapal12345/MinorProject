package com.WorldPopulationMeter.Payloads;

import lombok.Data;

@Data
public class JwtAuthResponse {

	private String Token;
	private String role;
}
