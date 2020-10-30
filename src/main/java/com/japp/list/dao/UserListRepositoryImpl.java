package com.japp.list.dao;

import com.japp.list.model.UserList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import java.util.List;

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

    @Override
    public List<UserList> findByProfileId(String profileId) {
        Query query = new Query(Criteria.where("profileId").is(profileId));
        return mongoTemplate.find(query, UserList.class);
    }

    @Override
    public UserList findByProfileIdAndListId(String profileId, String listId) {
        Query query = new Query(Criteria.where("profileId").is(profileId).and("listId").is(listId));
        List<UserList> userLists = mongoTemplate.find(query, UserList.class);
        if (!CollectionUtils.isEmpty(userLists))
            return userLists.get(0);
        return null;
    }

}
