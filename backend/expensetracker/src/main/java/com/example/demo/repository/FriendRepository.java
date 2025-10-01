package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.dtos.FriendDto;
import com.example.demo.models.FriendEntity;
import com.example.demo.models.UserEntity;

import jakarta.transaction.Transactional;

@Repository
public interface FriendRepository extends JpaRepository<FriendEntity,Long>{
	
	@Query("SELECT new com.example.demo.dtos.FriendDto(f.id, f.friendName) " +
		       "FROM FriendEntity f WHERE f.userEntity.id = :userId")
		List<FriendDto> getFriends(@Param("userId") Long userId);


	
	
	@Query("SELECT f FROM FriendEntity f " +
		       "JOIN f.groups g " +
		       "WHERE g.groupName = :groupName")
		List<FriendEntity> getGroupMembers(@Param("groupName") String groupName);

	FriendEntity findByFriendName(String friendName);
	
	 

 
	@Modifying
    @Transactional
    @Query("DELETE FROM FriendEntity f WHERE f.friendName = :friendName AND f.userEntity = :user")
    void deleteByFriendNameAndUserEntity(@Param("friendName") String friendName, 
                                         @Param("user") UserEntity user);

}
