package com.example.demo.models;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
 


@Entity
public class FriendEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String friendName;
	
	 @ManyToMany(mappedBy = "friends", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	  private List<GroupEntity> groups;
	 
	 @ManyToOne
	 @JoinColumn(name = "user_id")
	 private UserEntity userEntity;
	 
	
	 @OneToMany(mappedBy = "friend", cascade = CascadeType.ALL)
	 private List<TransactionShareEntity> transactionShares;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

 
	public String getFriendName() {
		return friendName;
	}

	public void setFriendName(String friendName) {
		this.friendName = friendName;
	}

	public List<GroupEntity> getGroups() {
		return groups;
	}

	public void setGroups(List<GroupEntity> groups) {
		this.groups = groups;
	}

	public List<TransactionShareEntity> getTransactionShares() {
		return transactionShares;
	}

	public void setTransactionShares(List<TransactionShareEntity> transactionShares) {
		this.transactionShares = transactionShares;
	}

	public UserEntity getUserEntity() {
		return userEntity;
	}

	public void setUserEntity(UserEntity userEntity) {
		this.userEntity = userEntity;
	}

	 
	
	


}
