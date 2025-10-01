package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.models.TransactionEntity;
import com.example.demo.models.UserEntity;
@Repository
public interface TransactionRepository extends JpaRepository<TransactionEntity,Long>{
	
	@Query(value = "SELECT * FROM transaction_entity WHERE user_id = :userId ORDER BY date_time DESC LIMIT 1", nativeQuery = true)
	TransactionEntity findLatestTransaction(@Param("userId") Long userId);

}
