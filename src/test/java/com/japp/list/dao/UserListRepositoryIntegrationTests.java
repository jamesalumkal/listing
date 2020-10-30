package com.japp.list.dao;

import com.japp.list.model.UserList;
import com.japp.list.model.UserListAccessType;
import com.japp.list.model.UserListProduct;
import com.japp.list.model.UserListType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
class UserListRepositoryIntegrationTests {

    @Autowired
    private UserListRepository userListRepository;

    @Test
    public void saveUserList_return() throws Exception {
        UserList userList  = getUserList();
        assertThat(userListRepository.save(userList).getListId()).isEqualTo(userList.getListId());
    }

    @Test
    public void getUserList_return() throws Exception {
        UserList userList  = getUserList();
        userListRepository.save(userList);
        assertThat(userListRepository.find(userList).getListName()).isEqualTo(userList.getListName());
    }

    @Test
    public void getUserListsByProfileId_returm() throws Exception {
        UserList userList  = getUserList();
        userListRepository.save(userList);

        assertThat(userListRepository.findByProfileId(userList.getProfileId()).size()).isEqualTo(1);
    }

    private UserList getUserList() {
        UserList userList = new UserList();
        userList.setListId("ListId100");
        userList.setProfileId("ProfId100");
        userList.setListName("ListName");
        userList.setListAccessType(UserListAccessType.PUBLIC);
        userList.setListType(UserListType.BLACK_FRIDAY);
        userList.setAllowedLimit(10);

        UserListProduct userListProduct100 = new UserListProduct();
        userListProduct100.setProductId("ProdId100");
        userListProduct100.setProductTitle("ProdTitle100");

        UserListProduct userListProduct101 = new UserListProduct();
        userListProduct101.setProductId("ProdId101");
        userListProduct101.setProductTitle("ProdTitle101");

        return userList;
    }

}