package com.WorldPopulationMeter.Controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


import com.WorldPopulationMeter.Entity.ContactForm;
import com.WorldPopulationMeter.Service.ContactFormService;

@Controller
public class UserMessageController {


	
	private static  Logger logger=LoggerFactory.getLogger(UserMessageController.class);
	
	@Autowired
	private ContactFormService contactService;
	
	@GetMapping("/contact")
	public String contact(Model model) {
		logger.info("Navigating to contact page");
	    model.addAttribute("Contact", new ContactForm());
	    return "contact";
	}

	
	@PostMapping("/submit-message")
	public String SubmitMessage(@ModelAttribute("Contact")ContactForm contact,@RequestParam("name")String name,@RequestParam("email") String email,@RequestParam("message")String message,Model model) {
		logger.info("Submitting message from user: {} with email: {} message :{}", name, email,message);
		contactService.SaveMessage(email, name, message);
		
		model.addAttribute("message","message sent succesfully");
		
		return "redirect:/contact";
	}
}
