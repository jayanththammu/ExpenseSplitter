package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dtos.AddGroupDto;
import com.example.demo.service.GroupService;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/groupserver")
public class GroupController {
	
	@Autowired
	private GroupService groupService;
	
	@PostMapping("/addgroup")
	public ResponseEntity<String> addGroup(@RequestBody AddGroupDto body,HttpSession session){
		
		return ResponseEntity.ok(groupService.addGroup(body, session));
	}
	
	@GetMapping("/getgroups")
	public ResponseEntity<?> getGroups(HttpSession session) {
		
		return ResponseEntity.ok(groupService.getAllGroups(session));
	}
}
