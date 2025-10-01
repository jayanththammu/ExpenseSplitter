package com.example.demo.models;


import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class UserEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@OneToMany(mappedBy = "userEntity", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<TransactionEntity> transactions;
	
	@OneToMany(mappedBy = "userEntity", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<FriendEntity> friends;
	
	 
	
	@OneToMany(mappedBy = "userEntity", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<GroupEntity> groups;

	private String firstName;
	private String lastName;
	private String userName;
	private String password;
	private String email;
	private String mobileNo;

	public String getFirstName() {
		return firstName;
	}

	public UserEntity setFirstName(String firstName) {
		this.firstName = firstName;
		return this;
	}

	public String getLastName() {
		return lastName;
	}

	public UserEntity setLastName(String lastName) {
		this.lastName = lastName;
		return this;
	}

	public String getUserName() {
		return userName;
	}

	public UserEntity setUserName(String userName) {
		this.userName = userName;
		return this;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public UserEntity setEmail(String email) {
		this.email = email;
		return this;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public UserEntity setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
		return this;
	}

	public Long getId() {
		return id;
	}

	public List<TransactionEntity> getTransactions() {
		return transactions;
	}

	public void setTransactions(List<TransactionEntity> transactions) {
		this.transactions = transactions;
	}

	public List<FriendEntity> getFriends() {
		return friends;
	}

	public void setFriends(List<FriendEntity> friends) {
		this.friends = friends;
	}

	public void setId(Long id) {
		this.id = id;
	}
	

}
