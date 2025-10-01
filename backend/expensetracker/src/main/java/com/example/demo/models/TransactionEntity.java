package com.example.demo.models;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
 
@Entity
public class TransactionEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@OneToMany(mappedBy = "transaction", cascade = CascadeType.ALL)
	private List<TransactionShareEntity> shares;
	
	@ManyToOne
	@JoinColumn(name = "group_id")  
	private GroupEntity group;
	
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private UserEntity userEntity;
	
	private String transactionName;
	private LocalDateTime dateTime;
	private double amount;
	private boolean isGroupTransaction;
	
 
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public List<TransactionShareEntity> getShares() {
		return shares;
	}
	public void setShares(List<TransactionShareEntity> shares) {
		this.shares = shares;
	}
	public GroupEntity getGroup() {
		return group;
	}
	public void setGroup(GroupEntity group) {
		this.group = group;
	}
	public String getTransactionName() {
		return transactionName;
	}
	public void setTransactionName(String transactionName) {
		this.transactionName = transactionName;
	}
	public LocalDateTime getDateTime() {
		return dateTime;
	}
	public void setDateTime(LocalDateTime dateTime) {
		this.dateTime = dateTime;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public boolean isIsGroupTransaction() {
		return isGroupTransaction;
	}
	public void setIsGroupTransaction(boolean isGroupTransaction) {
		this.isGroupTransaction = isGroupTransaction;
	}
	public UserEntity getUserEntity() {
		return userEntity;
	}
	public void setUserEntity(UserEntity userEntity) {
		this.userEntity = userEntity;
	}
	public boolean isGroupTransaction() {
		return isGroupTransaction;
	}
	public void setGroupTransaction(boolean isGroupTransaction) {
		this.isGroupTransaction = isGroupTransaction;
	}
	
	
	
	
	
	
	
	
}
