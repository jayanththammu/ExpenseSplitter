package com.example.demo.dtos;

import java.util.List;

public class FriendListDto {
    private List<String> friendNames;
    
    public List<String> getFriendNames() {
        return friendNames;
    }

    public void setFriendNames(List<String> friendNames) {
        this.friendNames = friendNames;
    }
}