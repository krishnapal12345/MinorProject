package com.WorldPopulationMeter.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import com.WorldPopulationMeter.Config.JwtHelper;
import com.WorldPopulationMeter.Entity.LoginResponse;
import com.WorldPopulationMeter.Entity.User;
import com.WorldPopulationMeter.Service.UserService;

import io.micrometer.core.annotation.Timed;
import io.micrometer.core.instrument.MeterRegistry;

import com.WorldPopulationMeter.Payloads.JwtAuthRequest;
import com.WorldPopulationMeter.Payloads.JwtAuthResponse;

@RestController
@RequestMapping("api/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtHelper jwtHelper;

    @Autowired
    private AuthenticationManager authenticationManager;
    
    @Autowired
    private MeterRegistry meterRegistry;

    @Autowired
    private PasswordEncoder passwordEncoder; 

    @PostMapping("/registration")
    @Timed(value="api.auth.registration", description="Time taken to registre new user",histogram=true)
    public ResponseEntity<?> saveUser(@RequestBody User user) {
        
    	
    	try {      
    		User savedUser =meterRegistry.timer("api.auth.registration").record(()->{
                return userService.save(user);
    		});
          return ResponseEntity.ok(savedUser);  
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error during registration: Please try again later.");
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> createToken(@RequestBody JwtAuthRequest request) {
        try {
            return meterRegistry.timer("api.auth.login").record(() -> {
                // Authenticate user
                this.authenticate(request.getUsername(), request.getPassword());

                // Load user details
                UserDetails userDetails = this.userDetailsService.loadUserByUsername(request.getUsername());

                // Generate token
                String token = this.jwtHelper.generateToken(userDetails);

                // Fetch user details
                User user = userService.findByUsername(request.getUsername());

                // Build response
                JwtAuthResponse response = new JwtAuthResponse();
                response.setToken(token);
                response.setRole(user.getRole());

                return ResponseEntity.ok(response);
            });
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Invalid username or password");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Login failed: Please try again later.");
        }
    }

   
    private void authenticate(String username, String password) {
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(username, password);
        authenticationManager.authenticate(authenticationToken);
    }
}
