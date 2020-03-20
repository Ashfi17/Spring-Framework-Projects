package com.example.ecommerce.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ecommerce.repository.NewUserRepo;
import com.example.ecommerce.repository.UserRepository;
import com.example.ecommerce.security.JwtProvider;
import com.example.ecommerce.util.JwtResponse;
import com.example.ecommerce.util.Users;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthRestApis {
	
	
	@Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    NewUserRepo userRepo;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtProvider jwtProvider;
    
    
    @PostMapping("/signin")
    public ResponseEntity<JwtResponse> authenticateUser(@RequestBody Map<String, Object> req) {

        // provide username/email and password to the
        // UsernamePasswordAuthenticationToken class
        // which is then applied used to initialize the authentication manager
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken((String) req.get("email"), (String) req.get("password")));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        // setting the authentication object in the security context to use it in the
        // application
        String jwt = jwtProvider.generateJwtToken(authentication);
        // generate jwt by using the authentication manager class
        return ResponseEntity.ok(new JwtResponse(jwt));
    }
    
    
    
    
    
    
    @PostMapping("/signup")
    public ResponseEntity<String> registerUser(@RequestBody Map<String, Object> req) {
        if (userRepo.existsByUsername((String) req.get("username"))) {
            // return responsecreator response
            return new ResponseEntity<String>("Fail -> Username is already taken!", HttpStatus.BAD_REQUEST);
        }

        if (userRepo.existsByEmail((String) req.get("email"))) {
            // return responsecreator response
            return new ResponseEntity<String>("Fail -> Email is already in use!", HttpStatus.BAD_REQUEST);
        }

        // Creating user's account
        Users user = new Users((String) req.get("name"), (String) req.get("username"), (String) req.get("email"),
                encoder.encode((String) req.get("password")));
        userRepo.save(user);

        return ResponseEntity.ok().body("User registered successfully!");
    }
    

}












