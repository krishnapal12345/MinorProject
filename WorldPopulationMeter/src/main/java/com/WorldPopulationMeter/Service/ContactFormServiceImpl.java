package com.WorldPopulationMeter.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.WorldPopulationMeter.Entity.ContactForm;
import com.WorldPopulationMeter.Repository.ContactFormRepository;

@Service
public class ContactFormServiceImpl implements ContactFormService{

	@Autowired
	private ContactFormRepository contactRepository;
	
	@Override
	public ContactForm save(ContactForm contact) {
		
		return contactRepository.save(contact);
	}
	
	 @Override
	    public void SaveMessage(String email, String name, String message) {
	        ContactForm contact = new ContactForm(name, email, message);
	        save(contact);
	        
	    }
}