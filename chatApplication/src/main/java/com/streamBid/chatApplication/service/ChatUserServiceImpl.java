package com.streamBid.chatApplication.service;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.streamBid.chatApplication.model.ChatUser;
import com.streamBid.chatApplication.repository.ChatUserRepository;




@Service
public class ChatUserServiceImpl implements ChatUserService {

	 Logger logger
     = Logger.getLogger(
    		 ChatUserServiceImpl.class.getName());
	 
	 
	@Autowired
	private ChatUserRepository chatUserRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub

		
		//loading user from database
		ChatUser user = this.chatUserRepository.findByUserName(username).orElseThrow(() -> new UsernameNotFoundException(username));
		
		logger.info("In find by username "+user);
		return  user;
		
	}

	@Override
	public List<ChatUser> getAllChatUsers() {
		// TODO Auto-generated method stub
		return chatUserRepository.findAll();
	}

	@Override
	public void saveChatUser(ChatUser chatUser) {
		// TODO Auto-generated method stub
		logger.info("Before Encyption");
		System.out.println(chatUser);
		logger.info(chatUser.getPassword());
		BCryptPasswordEncoder bcryptEncoder = new BCryptPasswordEncoder();
		chatUser.setPassword(bcryptEncoder.encode(chatUser.getPassword()));
	
		logger.info("After Encryption");
		logger.info(chatUser.getPassword());
		chatUser.setRole("USER");
		this.chatUserRepository.save(chatUser);
		
	}

	@Override
	public ChatUser getChatUserById(long id) {
		// TODO Auto-generated method stub
		logger.info("In getEmployeeById");
		Optional<ChatUser> optional = chatUserRepository.findById(id);
		ChatUser chatUser = null;
		if (optional.isPresent()) {
			chatUser = optional.get();
		} else {
			throw new RuntimeException(" Chat User not found for id :: " + id);
		}
		return chatUser;
		
	}

	@Override
	public void deleteChatUserById(long id) {
		// TODO Auto-generated method stub
		this.chatUserRepository.deleteById(id);
	}

	
}
