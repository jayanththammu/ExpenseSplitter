package com.example.demo.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class OtpEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private java.time.LocalTime localTime;
	private String userName;
	private String Otp;
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getOtp() {
		return Otp;
	}
	public void setOtp(String otp) {
		Otp = otp;
	}
	public Long getId() {
		return id;
	}
	public java.time.LocalTime getLocalTime() {
		return localTime;
	}
	public void setLocalTime(java.time.LocalTime localTime) {
		this.localTime = localTime;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	

}
