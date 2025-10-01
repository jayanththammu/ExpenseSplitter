package com.example.demo.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.models.GroupEntity;
@Repository
public interface GroupRepository extends JpaRepository<GroupEntity,Long>{

	@Query("SELECT DISTINCT g FROM GroupEntity g " +
		       "JOIN g.friends f " +
		       "WHERE f.userEntity.id = :userId")
	List<GroupEntity> findGroupsByUserId(@Param("userId") Long userId);
	
	@Query(value = "SELECT g.id," +
            " g.group_name, " +
            " GROUP_CONCAT(f.friend_name ORDER BY f.friend_name SEPARATOR ', ') AS group_friends " +
            "FROM group_entity g " +
            "JOIN user_entity u ON u.id = g.user_id " +
            "JOIN group_friends gf ON g.id = gf.group_id " +
            "JOIN friend_entity f ON f.id = gf.friend_id " +
            "WHERE u.id = :userId " +
            "GROUP BY g.id, g.group_name, g.user_id",
    nativeQuery = true)
List<Map<String, Object>> findGroupsWithFriends(@Param("userId") Long userId);
	
	
	GroupEntity findByGroupName(String groupName);
	
	
	@Query("SELECT g FROM GroupEntity g LEFT JOIN FETCH g.friends WHERE g.id = :groupId")
	GroupEntity findByGroupIdWithFriends(@Param("groupId") Long id);
	
	
}
