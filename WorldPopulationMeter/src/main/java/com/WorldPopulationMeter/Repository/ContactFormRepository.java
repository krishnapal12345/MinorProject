package com.WorldPopulationMeter.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.WorldPopulationMeter.Entity.ContactForm;

@Repository
public interface ContactFormRepository extends JpaRepository<ContactForm,Integer>{

}
