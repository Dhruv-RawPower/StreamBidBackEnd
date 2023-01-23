package com.streamBid.chatApplication.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.streamBid.chatApplication.model.ChatUser;



public interface ChatUserRepository extends JpaRepository<ChatUser, Long>{
	
	Optional<ChatUser> findByEmail(String Username);

	Optional<ChatUser> findByUserName(String userName);


}
