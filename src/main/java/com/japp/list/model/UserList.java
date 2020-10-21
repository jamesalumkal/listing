package com.japp.list.model;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Root entity of the UserList Aggregate
 */

@Getter
@Setter
public class UserList {

    private String listName;
    private String profileId;
    private UserListType listType;
    private UserListAccessType listAccessType;
    private List<UserListProduct> userListProducts;

    public UserList() {
        userListProducts = new ArrayList<>();
    }

    public boolean addProduct(UserListProduct prod) {
        userListProducts.add(prod);
        return true;
    }
}
