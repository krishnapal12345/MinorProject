package com.WorldPopulationMeter.Repository;

import org.springframework.stereotype.Service;
import com.WorldPopulationMeter.Entity.ContactForm;

@Service
public interface ContactFormService {

	ContactForm save(ContactForm contact);

	void SaveMessage(String email, String name, String message);
}
