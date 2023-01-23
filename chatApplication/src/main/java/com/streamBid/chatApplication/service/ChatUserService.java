package com.streamBid.chatApplication.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.streamBid.chatApplication.model.ChatUser;

@Service
public interface ChatUserService extends UserDetailsService {
	List<ChatUser> getAllChatUsers();
	void saveChatUser(ChatUser employee);
	ChatUser getChatUserById(long id);
	void deleteChatUserById(long id);
}
