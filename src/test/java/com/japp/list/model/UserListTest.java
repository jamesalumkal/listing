package com.japp.list.model;

import org.junit.jupiter.api.Test;

public class UserListTest {

    @Test
    public void create_emptyUser() throws Exception {
        UserList userList = new UserList();
    }

    @Test
    public void setNameAndProfileId() throws Exception {
        UserList userList = new UserList();
        userList.setListName("UserListName");
        userList.setProfileId("ProfId100");
    }


}
