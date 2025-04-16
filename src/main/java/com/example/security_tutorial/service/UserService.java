package com.example.security_tutorial.service;

import com.example.security_tutorial.model.User;
import com.example.security_tutorial.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private AuthenticationManager authManager;
    @Autowired
    private UserRepo repo;
    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);
    @Autowired
    private JWTService jwtService;

    public User register(User user){
        user.setPassword(encoder.encode(user.getPassword()));
        return this.repo.save(user);
    }

    public String verify(User user) {
        Authentication auth = authManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));

        if(auth.isAuthenticated()){
            System.out.println(auth);
            return jwtService.generateToken(user.getUsername());
        }

        return "Fail";
    }
}
