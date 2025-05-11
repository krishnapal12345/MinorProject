package com.WorldPopulationMeter.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.WorldPopulationMeter.Entity.User;
import com.WorldPopulationMeter.Repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Override
    public User save(User user) {
        // Ensure the role has "ROLE_" prefix if it's missing
        String role = user.getRole();
        if (!role.startsWith("ROLE_")) {
            role = "ROLE_" + role;
        }

        User newUser = new User(user.getUsername(),
                passwordEncoder.encode(user.getPassword()),
                role,
                user.getFullname());

        return userRepository.save(newUser);
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
