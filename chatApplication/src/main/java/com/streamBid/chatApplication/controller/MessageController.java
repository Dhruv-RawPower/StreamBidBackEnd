package com.streamBid.chatApplication.controller;

import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.streamBid.chatApplication.model.Message;

@Controller
public class MessageController {
	
	// Create a Logger
    Logger logger
        = Logger.getLogger(
        		MessageController.class.getName());

    private final SimpMessagingTemplate template;

    @Autowired
    MessageController(SimpMessagingTemplate template){
        this.template = template;
    }
    
	@MessageMapping("/send/message")
	@ResponseBody
	public void getContent(@RequestBody Message message) {
		System.out.println(message);
		
		try {
               //processing
            Thread.sleep(1000);
            this.template.convertAndSend("/message",  message);

          } catch (Exception e) {
            e.printStackTrace();
          }
     //     return message;
    }
	
}
