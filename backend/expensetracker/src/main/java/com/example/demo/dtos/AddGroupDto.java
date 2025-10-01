package com.example.demo.dtos;

 
import java.util.List;

public class AddGroupDto {

	
	private String groupName;
	private List<Long> friendsIds;
	 
	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public List<Long> getFriendsIds() {
		return friendsIds;
	}

	public void setFriendsIds(List<Long> friendsIds) {
		this.friendsIds = friendsIds;
	}

	@Override
	public String toString() {
		return "AddGroupDto [groupName=" + groupName + ", friendsIds=" + friendsIds + "]";
	}
	
	
	
	
	
}
