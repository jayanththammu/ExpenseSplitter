package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dtos.AddGroupDto;
import com.example.demo.models.FriendEntity;
import com.example.demo.models.GroupEntity;
import com.example.demo.models.UserEntity;
import com.example.demo.repository.FriendRepository;
import com.example.demo.repository.GroupRepository;

import jakarta.servlet.http.HttpSession;

@Service
public class GroupService {

	
	@Autowired
	private GroupRepository groupRepository;
	
	@Autowired
	private FriendRepository friendRepository;
	
	public String addGroup(AddGroupDto body,HttpSession session) {
		
		UserEntity user = (UserEntity) session.getAttribute("LoggedInUser");
		
		System.out.println(body);
		if(user == null) {
			throw new Error("User Not Found");
		}
		
		GroupEntity group = new GroupEntity();
		
		group.setGroupName(body.getGroupName());
		group.setUserEntity(user);
		
		List<FriendEntity> friends = new ArrayList<>();
		
		
		List<Long> friendIds = body.getFriendsIds();
		if (friendIds == null || friendIds.isEmpty()) {
		    throw new IllegalArgumentException("Friends list cannot be empty");
		}

		for(Long friend : body.getFriendsIds()) {
			
			FriendEntity friendEntity = friendRepository.findById(friend).orElse(null);
			
			if(friendEntity == null) {
				 throw new RuntimeException("Friend not found with id: " + friend);
			}
			friends.add(friendEntity);
		}
		group.setFriends(friends);
		
		groupRepository.save(group);
		return "Group Saved Successfully";
	}
	
	public List<Map<String, Object>> getAllGroups(HttpSession session) {
		
		UserEntity user = (UserEntity) session.getAttribute("LoggedInUser");
		
		if(user == null) {
			throw new Error("User Not Found");
		}
		 
		List<Map<String, Object>> groups = groupRepository.findGroupsWithFriends(user.getId());
		
		return groups;
		 
		
	}
	
}
