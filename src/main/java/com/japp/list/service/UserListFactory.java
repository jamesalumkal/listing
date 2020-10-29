package com.japp.list.service;

import com.japp.list.model.UserList;
import com.japp.list.model.UserListAccessType;
import com.japp.list.model.UserListType;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * Factory of the UserList Aggregate
 */
@Component
public class UserListFactory {

    public UserList createUserList(UserListType userListType, UserListAccessType userListAccessType) {
        UserList list = new UserList();
        list.setListType(userListType);
        list.setListAccessType(userListAccessType);

        list.setListId(generateUserListUniqueId());
        return list;
    }

    private String generateUserListUniqueId() {
        return UUID.randomUUID().toString();
    }
}
