package com.streamBid.chatApplication.controller;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.streamBid.chatApplication.model.ChatUser;
import com.streamBid.chatApplication.service.ChatUserService;

import jakarta.servlet.http.HttpServletResponse;

@Controller
@CrossOrigin(origins = "http://localhost:4200")
public class DashboardController {
	
	Logger logger
    = Logger.getLogger(
    		DashboardController.class.getName());
	
	@Autowired
    private ChatUserService chatUserService;
	
	@PostMapping("/getAllUsers")
	@ResponseBody
	public List<ChatUser> getAllUsers() {		
		
		logger.info("In getAllUsers.");
		
		// get users from database
		return chatUserService.getAllChatUsers();
	}
    

}
