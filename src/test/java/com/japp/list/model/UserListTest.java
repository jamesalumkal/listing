package com.japp.list.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class UserListTest {

    @Test
    public void create_emptyUser() throws Exception {
        UserList userList = new UserList();
    }

    @Test
    public void setNameAndProfileId() throws Exception {
        UserList userList = new UserList();

        final String userListName = "UserListName";
        final String userProfId = "ProfId100";

        userList.setListName(userListName);
        userList.setProfileId(userProfId);

        Assertions.assertEquals(userListName, userList.getListName());
        Assertions.assertEquals(userProfId, userList.getProfileId());
    }

    @Test
    public void setListType() throws Exception {
        UserList userList = new UserList();
        userList.setListType(UserListType.REGULAR);

        Assertions.assertEquals(UserListType.REGULAR, userList.getListType());
    }



}
