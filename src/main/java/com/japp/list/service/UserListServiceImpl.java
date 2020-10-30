package com.japp.list.service;

import com.japp.list.config.ListConfig;
import com.japp.list.dao.UserListRepository;
import com.japp.list.model.UserList;
import com.japp.list.model.UserListAccessType;
import com.japp.list.model.UserListProduct;
import com.japp.list.model.UserListType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserListServiceImpl implements UserListService {

    @Autowired
    private UserListFactory userListFactory;

    @Autowired
    private ListConfig listConfig;

    @Autowired
    private UserListRepository userListRepository;

    @Override
    public UserList createUserList(String listName, String profileId, UserListAccessType userListAccessType,
                                   UserListType userListType, List<UserListProduct> userListProducts) {
        UserList userList = userListFactory.createUserList(userListType, userListAccessType);
        userList.setAllowedLimit(listConfig.getAllowedsize());
        userList.setListName(listName);
        userList.setProfileId(profileId);
        userListProducts.forEach(p -> {
            userList.getUserListProducts().add(p);
        });

        return userListRepository.save(userList);
    }

    @Override
    public List<UserList> getLists(String profileId) {
        return userListRepository.findByProfileId(profileId);
    }

    @Override
    public UserList getList(String profileId, String listId) {
        return userListRepository.findByProfileIdAndListId(profileId, listId);
    }


}
