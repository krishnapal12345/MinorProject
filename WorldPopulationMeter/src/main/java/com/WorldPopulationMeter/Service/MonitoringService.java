package com.WorldPopulationMeter.Service;

import org.springframework.stereotype.Service;

import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Timer;
import io.prometheus.metrics.core.metrics.Gauge;

@Service
public class MonitoringService {

	private final MeterRegistry meterRegistry;
	
	public MonitoringService(MeterRegistry meterRegistry) {
		this.meterRegistry=meterRegistry;
	}
	
	public void  recordSubmission(String countryName,String stateName) {
	 
		meterRegistry.counter("api.submit.country.state.block.timer",
				              "countryName",countryName,
				              "stateName",stateName
				              ).increment();
	}
}
