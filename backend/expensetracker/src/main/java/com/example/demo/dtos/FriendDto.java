package com.example.demo.dtos;

public class FriendDto {
	 private Long id;
	    private String friendName;

	    public FriendDto(Long id, String friendName) {
	        this.id = id;
	        this.friendName = friendName;
	    }

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
	    
	    

}
