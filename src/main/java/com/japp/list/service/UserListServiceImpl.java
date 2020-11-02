package com.japp.list.service;

import com.japp.list.config.ListConfig;
import com.japp.list.dao.UserListRepository;
import com.japp.list.exceptions.ProductAlreadyExistsException;
import com.japp.list.exceptions.SizeLimitExceededException;
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
                                   UserListType userListType, List<UserListProduct> userListProducts)
                throws  SizeLimitExceededException, ProductAlreadyExistsException {
        UserList userList = userListFactory.createUserList(userListType, userListAccessType);
        userList.setListName(listName);
        userList.setProfileId(profileId);
        for (UserListProduct product : userListProducts) {
            userList.addProduct(product, listConfig.getAllowedsize());
        }
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

    @Override
    public UserList addProducts(String profileId, String listId, List<UserListProduct> userListProducts)
            throws SizeLimitExceededException, ProductAlreadyExistsException {
        UserList userList = userListRepository.findByProfileIdAndListId(profileId, listId);
        if (null != userList) {
            for (UserListProduct prod : userListProducts) {
                userList.addProduct(prod, listConfig.getAllowedsize());
            }
        }
        return userListRepository.save(userList);
    }


}
