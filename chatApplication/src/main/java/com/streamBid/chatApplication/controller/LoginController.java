package com.streamBid.chatApplication.controller;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

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
        jwtResponse.setImageUrl(map.get("imageUrl").toString());
        jwtResponse.setToken(token);
        System.out.println("jwtRespone "+ jwtResponse);
        return jwtResponse;
    }

    /*    
    @RequestMapping(value = "/getCurrentUser", method = RequestMethod.GET)
	public ResponseEntity<?> getCurrentUser() {
		
    	logger.info("Inside login from angular");
    	return new ResponseEntity<>("Connection Successful", HttpStatus.OK);	 
    	
    }*/
    
    //Register User
    @PostMapping(value= "/saveChatUser", consumes = {MediaType.APPLICATION_JSON_VALUE,MediaType.MULTIPART_FORM_DATA_VALUE} )
	public ResponseEntity<?> saveEmployee(@RequestParam(name = "credentials") String chatUser,  @RequestParam(value = "file", required = true) MultipartFile file, HttpServletResponse response) {		
		
		logger.info("In saveChatUser.");
		ObjectMapper mapper = new ObjectMapper();
		ChatUser chatObj = null;
        try {
        	 chatObj = mapper.readValue(chatUser, ChatUser.class);
            System.out.println("ChatUser = " + chatObj);

           
        } catch (IOException e) {
            e.printStackTrace();
        }
		System.out.println(chatUser);
		
		String fileName = file.getOriginalFilename();
	    try {
	      file.transferTo( new File("C:\\Users\\dhruv\\Projects\\Angular\\Project\\StreamBid\\StreamBidAngular\\StreamBidAngularUI\\src\\assets\\images\\" + fileName));
	    } catch (Exception e) {
	      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	    } 
		
		// save employee to database
		chatUserService.saveChatUser(chatObj);
		logger.info("done post");
		return new ResponseEntity<>(null, HttpStatus.OK);
	}
    
    
}
