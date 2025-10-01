package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dtos.AddFriendDto;
import com.example.demo.service.FriendService;

import jakarta.servlet.http.HttpSession;


@RestController
@RequestMapping("/friendserver")
public class FriendController {
	
	@Autowired
	private FriendService friendService;
	
	@PostMapping("/addfriend")
	public ResponseEntity<Void> addFriend(@RequestBody AddFriendDto body ,HttpSession session) {
		
		friendService.addFriend(body, session);
		return ResponseEntity.ok().build();
	}
	
	@GetMapping("/getfriends")
	public ResponseEntity<?> getFriends(HttpSession session) {
		
		return ResponseEntity.ok(friendService.getFriends(session));
	}
	
	@DeleteMapping("/deleteFriend")
	public ResponseEntity<String> deleteFriend(@RequestBody AddFriendDto body,HttpSession session){
		
		return ResponseEntity.ok(friendService.deleteFriendByName(body,session));
	}
	
	
}
