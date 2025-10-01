package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dtos.AddFriendDto;
import com.example.demo.dtos.FriendDto;
import com.example.demo.dtos.FriendListDto;
import com.example.demo.models.FriendEntity;
import com.example.demo.models.UserEntity;
import com.example.demo.repository.FriendRepository;

import jakarta.servlet.http.HttpSession;

@Service
public class FriendService {
 
	@Autowired
	private FriendRepository friendRepository;
	
	
	public void addFriend(AddFriendDto body ,HttpSession session) {
		
		
		try {
			
			UserEntity user =(UserEntity) session.getAttribute("LoggedInUser");
			if(user == null) {
				throw new Error("User Not Found");
			}
			
			FriendEntity friendEntity = new FriendEntity();
			
			friendEntity.setFriendName(body.getFriendName());
			friendEntity.setUserEntity(user);
			friendRepository.save(friendEntity);
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		 
		
		return ;
		
	}
	
	public List<FriendDto>  getFriends(HttpSession session) {
		
		UserEntity user = (UserEntity) session.getAttribute("LoggedInUser");
		Long userId = user.getId();
		
		List<FriendDto> friends = friendRepository.getFriends(userId);
		 
		
		return friends;
		
		
	}
	
	public String deleteFriendByName(AddFriendDto body,HttpSession session) {
		
		try {
			 UserEntity user =(UserEntity) session.getAttribute("LoggedInUser");
			
			friendRepository.deleteByFriendNameAndUserEntity(body.getFriendName(),user);
			
		} catch (Exception e) {
			
			e.printStackTrace();
		 
		}
		
		return "Deleted Successfully";
	}
	
}



