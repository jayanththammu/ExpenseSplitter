package com.example.demo.repository;

import org.springframework.stereotype.Repository;

import com.example.demo.models.OtpEntity;
@Repository
public interface OtpRepository extends org.springframework.data.jpa.repository.JpaRepository<OtpEntity, Long>{
		
	OtpEntity findByUserName(String userName);
	void deleteByUserName(String userName);
}
