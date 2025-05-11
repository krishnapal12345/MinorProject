package com.WorldPopulationMeter.Controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
public class HomeController {

	@GetMapping("/home")
	public String HomePage() {
		return "home";
	}
	
	@GetMapping("/about")
	public String about() {
		return "about";
	}

}
