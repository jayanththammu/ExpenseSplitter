package com.example.demo.models;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class GroupEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	 
	@Column(nullable = false,unique = true)
	private String groupName;

	@OneToMany(mappedBy = "group", cascade = CascadeType.ALL)
	private List<TransactionEntity> transactions;
	
	 

	@ManyToOne()
	@JoinColumn(name ="user_id",nullable = false)
	private UserEntity userEntity;

	
	@ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	@JoinTable(name = "group_friends", joinColumns = @JoinColumn(name = "group_id"), inverseJoinColumns = @JoinColumn(name = "friend_id"))
	private List<FriendEntity> friends;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public List<FriendEntity> getFriends() {
		return friends;
	}

	public void setFriends(List<FriendEntity> friends) {
		this.friends = friends;
	}

	public List<TransactionEntity> getTransactions() {
		return transactions;
	}

	public void setTransactions(List<TransactionEntity> transactions) {
		this.transactions = transactions;
	}
	public UserEntity getUserEntity() {
		return userEntity;
	}

	public void setUserEntity(UserEntity userEntity) {
		this.userEntity = userEntity;
	}
	 

}
