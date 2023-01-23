package com.streamBid.chatApplication.controller;

import java.util.Map;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.streamBid.chatApplication.model.ChatUser;
import com.streamBid.chatApplication.model.JwtRequest;
import com.streamBid.chatApplication.model.JwtResponse;
import com.streamBid.chatApplication.service.ChatUserService;
import com.streamBid.chatApplication.service.TokenService;
import com.streamBid.chatApplication.utilities.JwtUtil;

import jakarta.servlet.http.HttpServletResponse;




@Controller
@CrossOrigin(origins = "http://localhost:4200")
public class LoginController {
	
	// Create a Logger
    Logger logger
        = Logger.getLogger(
        		LoginController.class.getName());
    
    @Autowired
    private ChatUserService chatUserService;
    
    private  TokenService tokenService;
    private  AuthenticationManager authenticationManager;

    public  LoginController(TokenService tokenService, AuthenticationManager authenticationManager) {
        this.tokenService = tokenService;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/token")
    @ResponseBody
    public JwtResponse token(@RequestBody JwtRequest userLogin) throws AuthenticationException, JSONException, JsonProcessingException {
    	logger.info(userLogin.getUsername()+" "+userLogin.getPassword());
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userLogin.getUsername(), userLogin.getPassword()));
        
        ObjectMapper mapper = new ObjectMapper();
        String user = mapper.writeValueAsString(authentication.getPrincipal());
        
        
        Map<String,Object> map = mapper.readValue(user, Map.class);
        
        
        System.out.println();
        
        
        final String token = tokenService.generateToken(authentication);
        
        JwtResponse jwtResponse = new JwtResponse();
        jwtResponse.setFullName(map.get("firstName").toString().concat(" ").concat(map.get("lastName").toString()));
        jwtResponse.setToken(token);
        return jwtResponse;
    }

    /*    
    @RequestMapping(value = "/getCurrentUser", method = RequestMethod.GET)
	public ResponseEntity<?> getCurrentUser() {
		
    	logger.info("Inside login from angular");
    	return new ResponseEntity<>("Connection Successful", HttpStatus.OK);	 
    	
    }*/
    
    //Register User
    @PostMapping("/saveChatUser")
	public ResponseEntity<?> saveEmployee(@RequestBody ChatUser chatUser, HttpServletResponse response) {		
		
		logger.info("In saveChatUser.");
		System.out.println(chatUser);
		// save employee to database
		chatUserService.saveChatUser(chatUser);
		logger.info("done post");
		return new ResponseEntity<>(null, HttpStatus.OK);
	}
    
    
}
