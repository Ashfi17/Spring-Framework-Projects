package com.example.blog.controller;


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

import com.example.blog.repository.UserRepository;
import com.example.blog.security.JwtProvider;
import com.example.blog.security.JwtResponse;
import com.example.blog.util.Busers;

import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;


@Transactional
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/blogs/auth")
public class AuthController {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtProvider jwtProvider;

    // ---------------------------------------------------------------------------------------------------//
    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@RequestBody Map<String, Object> req) {

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
    // ---------------------------------------------------------------------------------------------------//

    @PostMapping("/signup")
    public ResponseEntity<String> registerUser(@RequestBody Busers user) {
        
        if (userRepository.existsByEmail((String) user.getEmail())) {
            // return responsecreator response
            return new ResponseEntity<String>("Fail -> Email is already in use!", HttpStatus.BAD_REQUEST);
        }

        // Creating user's account
        user.setPassword(encoder.encode(user.getPassword()));
        entityManager.persist(user);
        entityManager.flush();

        return ResponseEntity.ok().body("User registered successfully!");
    }
	

}
