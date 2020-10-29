package com.japp.list.service;

import com.japp.list.model.UserListAccessType;
import com.japp.list.model.UserListType;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;


class UserListFactoryTests {

    @Test
    public void createUserList_return() throws Exception {
        UserListFactory userListFactory = new UserListFactory();
        assertThat(userListFactory.createUserList(UserListType.REGULAR, UserListAccessType.PUBLIC).getListId()).isNotNull();
    }

}