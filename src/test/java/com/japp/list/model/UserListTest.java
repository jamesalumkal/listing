package com.japp.list.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class UserListTest {

    @Test
    public void create_emptyUserList() throws Exception {
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


    @Test
    public void setListAccessType() throws Exception {
        UserList userList = new UserList();
        userList.setListAccessType(UserListAccessType.PUBLIC);

        Assertions.assertEquals(UserListAccessType.PUBLIC, userList.getListAccessType());
    }

    @Test
    public void addProducts() throws Exception {
        UserList userList = new UserList();
        UserListProduct product = new UserListProduct();
        userList.addProduct(product);

        Assertions.assertEquals(1, userList.getUserListProducts().size());
    }
}
