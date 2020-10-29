package com.japp.list.service;

import com.japp.list.config.ListConfig;
import com.japp.list.model.UserList;
import com.japp.list.model.UserListAccessType;
import com.japp.list.model.UserListProduct;
import com.japp.list.model.UserListType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserListServiceIntegrationTests {

    @Autowired
    private UserListService userListService;

    @Test
    public void createEmptyUserList_returnUserList() throws Exception {
        String userListName = "UserListName";
        String profileId = "ProfId100";
        UserListType userListType = UserListType.REGULAR;
        UserListAccessType userListAccessType = UserListAccessType.PUBLIC;
        List<UserListProduct> userListProducts = new ArrayList<>();

        UserList userList = userListService.createUserList(userListName, profileId, userListAccessType, userListType, userListProducts);
        Assertions.assertNotNull(userList.getListId());
        Assertions.assertEquals(userListName, userList.getListName());
        Assertions.assertEquals(10, userList.getAllowedLimit());
    }



}