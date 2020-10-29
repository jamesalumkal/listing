package com.japp.list.dao;

import com.japp.list.model.UserList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UserListRepositoryImpl implements UserListRepository {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public UserList save(UserList userList) {
        return mongoTemplate.save(userList);
    }

    @Override
    public UserList find(UserList userList) {
        return mongoTemplate.findById(userList.getListId(), UserList.class);
    }
}
