package com.japp.list.service;

import com.japp.list.model.UserListAccessType;
import com.japp.list.model.UserListType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

class UserListFactoryTests {

    @Test
    public void createUserList_return() throws Exception {
        UserListFactory userListFactory = new UserListFactory();
        Assertions.assertNotNull(userListFactory.createUserList(UserListType.REGULAR, UserListAccessType.PUBLIC).getListId());
    }

}