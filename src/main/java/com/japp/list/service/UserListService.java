package com.japp.list.service;

import com.japp.list.exceptions.ProductAlreadyExistsException;
import com.japp.list.exceptions.SizeLimitExceededException;
import com.japp.list.model.UserList;
import com.japp.list.model.UserListAccessType;
import com.japp.list.model.UserListProduct;
import com.japp.list.model.UserListType;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserListService {

    public UserList createUserList(String listName, String profileId,
                                   UserListAccessType userListAccessType, UserListType userListType,
                                   List<UserListProduct> userListProducts)
            throws SizeLimitExceededException, ProductAlreadyExistsException;


    public List<UserList> getLists(String profileId);


    public UserList getList(String profileId, String listId);

    public UserList addProducts(String profileId, String listId, List<UserListProduct> userListProducts)
            throws SizeLimitExceededException, ProductAlreadyExistsException;

    public UserList removeProducts(String profileId, String listId, List<UserListProduct> userListProducts);
}
