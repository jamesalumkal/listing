package com.japp.list.service;

import com.japp.list.model.UserList;
import com.japp.list.model.UserListAccessType;
import com.japp.list.model.UserListProduct;
import com.japp.list.model.UserListType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


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
        assertThat(userList.getListId()).isNotNull();
        assertThat(userList.getListName()).isEqualTo(userListName);
        assertThat(userList.getAllowedLimit()).isEqualTo(10);
    }

    @Test
    public void getUserListsByProfileId_returnAllUserLists() throws Exception {
        String userListName = "UserListName";
        String profileId = "ProfId101";
        UserListType userListType = UserListType.REGULAR;
        UserListAccessType userListAccessType = UserListAccessType.PUBLIC;
        List<UserListProduct> userListProducts = new ArrayList<>();
        userListService.createUserList(userListName, profileId, userListAccessType, userListType, userListProducts);

        userListName = "UserListAnotherName";
        userListService.createUserList(userListName, profileId, userListAccessType, userListType, userListProducts);

        assertThat(userListService.getLists(profileId).size()).isEqualTo(2);
    }

    @Test
    public void getUserListByProfileIdAndListId_returnValidUserList() throws Exception {
        String userListName = "UserListName";
        String profileId = "ProfId102";
        UserListType userListType = UserListType.REGULAR;
        UserListAccessType userListAccessType = UserListAccessType.PUBLIC;
        List<UserListProduct> userListProducts = new ArrayList<>();
        UserList userListFromDB = userListService.createUserList(userListName, profileId, userListAccessType, userListType, userListProducts);

        assertThat(userListService.getList(profileId, userListFromDB.getListId()).getListName()).isEqualTo(userListName);
    }
}