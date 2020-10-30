package com.japp.list.dao;

import com.japp.list.model.UserList;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserListRepository {

    public UserList save(UserList userList);
    public UserList find(UserList userList);
    public List<UserList> findByProfileId(String profileId);
    public UserList findByProfileIdAndListId(String profileId, String listId);
}
