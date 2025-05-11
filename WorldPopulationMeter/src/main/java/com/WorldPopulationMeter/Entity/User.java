package com.WorldPopulationMeter.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name="users")
@Data
public class User {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private String username;
    private String password;
    private String role; //Eg: ADMIN,USER
    private String fullname;

    public User () {
    	
    }
    public User(String username,String password,String role,String fullname) {
    	this.fullname=fullname;
    	this.username=username;
    	this.password=password;
    	this.role=role;
    }

}
