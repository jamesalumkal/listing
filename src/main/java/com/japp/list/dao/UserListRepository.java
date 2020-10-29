package com.japp.list.dao;

import com.japp.list.model.UserList;
import org.springframework.stereotype.Repository;

@Repository
public interface UserListRepository {

    public UserList save(UserList userList);
    public UserList find(UserList userList);

}
