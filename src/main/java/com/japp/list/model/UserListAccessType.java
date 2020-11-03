package com.japp.list.model;

public enum UserListAccessType {
    PUBLIC("Public"),
    PRIVATE("Private");

    private String userListAccessName;
    UserListAccessType(String userListAccessName)
    {
        this.userListAccessName = userListAccessName;
    }

    public String userListAccessName() {
        return userListAccessName;
    }

}
