package com.example.blog.security;


import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.blog.service.AuthService;



public class JwtAuthTokenFilter extends OncePerRequestFilter{

	@Autowired
    private JwtProvider tokenProvider;

    @Autowired
    private AuthService userDetailsService;

    private static final Logger logger = LoggerFactory.getLogger(JwtAuthTokenFilter.class);

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        try {

            String jwt = getJwt(request);
            if (jwt != null && tokenProvider.validateJwtToken(jwt)) {
                String email = tokenProvider.getEmailFromJwtToken(jwt);
                System.out.println(email);
                UserDetails userDetails = userDetailsService.loadUserByEmail(email);
                // fetching user info by name/email/etc using method
                // UsernamePasswordAuthenticationToken
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities());
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                // set user details in the authentication manager
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        } catch (Exception e) {
            logger.error("CanNOT set user authentication -> Message: {}", e);
        }

        filterChain.doFilter(request, response);// invokes any other filters if applied
    }

    private String getJwt(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");
        // getting data from the http request header
        System.out.println("here");
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            System.out.println("in here");
            return authHeader.replace("Bearer ", "");
        }
        return null;
    }
	
	

}
