package com.example.demo.service;

import java.time.LocalTime;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.demo.dtos.OtpDto;
import com.example.demo.models.OtpEntity;
import com.example.demo.models.UserEntity;
import com.example.demo.repository.OtpRepository;
import com.example.demo.repository.UserRepository;

import jakarta.transaction.Transactional;
@org.springframework.stereotype.Service
public class MailService {
	
	@Autowired
	private JavaMailSender mailSender;
	
	@Autowired
	private OtpRepository otpRepository;
	
	@Autowired
	private PasswordEncoder encoder;
	
	@Autowired
	private UserRepository userRepository;
	
	private static final String fromMail ="njk870@gmail.com";
	
	private String generateOtp() {
		Random random = new Random();
		
		String otp =String.format("%06d", random.nextInt(1000000));
		return otp;
	}
	
	public String sendOtp(OtpDto body) {
		
		UserEntity user = userRepository.findByUserName(body.getUserName());
		
		if(user == null) {
			throw new Error("User Not Found");
		}
		
		SimpleMailMessage message = new SimpleMailMessage();
		
		message.setFrom(fromMail);
		message.setTo(user.getEmail());
		message.setSubject("Password Change Request");
		String otp = generateOtp();
		message.setText("Your otp was -"+otp+" valid for 10min");
		
		OtpEntity otpEntity = new OtpEntity();
		java.time.LocalTime time = LocalTime.now().plusMinutes(10);
		otpEntity.setLocalTime(time);
		String hashedOtp = encoder.encode(otp);
		otpEntity.setOtp(hashedOtp);
		otpEntity.setUserName(user.getUserName());
		otpRepository.save(otpEntity);
		mailSender.send(message);
		
		return "Otp Sent Successfully";
		
		
		
	}
	
	@Transactional
	public boolean verifyOtp(OtpDto body) {
		
		OtpEntity otpEntity = otpRepository.findByUserName(body.getUserName());
		if(otpEntity == null) {
			return false;
		}
		
		if(!encoder.matches(body.getOtp(),otpEntity.getOtp()) || java.time.LocalTime.now().isAfter(otpEntity.getLocalTime())) {
			return false;
		}

	 
		otpRepository.deleteByUserName(body.getUserName());
		
		return true;
		
		
	}
	

}
