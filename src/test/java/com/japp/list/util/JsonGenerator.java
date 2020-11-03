package com.japp.list.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.japp.list.exceptions.ProductAlreadyExistsException;
import com.japp.list.exceptions.SizeLimitExceededException;
import com.japp.list.model.UserList;
import com.japp.list.model.UserListAccessType;
import com.japp.list.model.UserListProduct;
import com.japp.list.model.UserListType;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JsonGenerator {

    public static void main(String args[]) {
        JsonGenerator jsonGenerator = new JsonGenerator();

        jsonGenerator.gnerateUserList();
    }

    private void gnerateUserList() {
       ObjectMapper mapper = new ObjectMapper();
        try {
            mapper.writeValue(new File("UserList.txt"), getDummyUserList("100", "name"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private UserList getDummyUserList(String profileId, String userListName) {
        UserList userList = new UserList();
        userList.setListName(userListName);
        userList.setProfileId(profileId);
        userList.setListType(UserListType.REGULAR);
        userList.setListAccessType(UserListAccessType.PUBLIC);
        List<UserListProduct> userListProducts = getDummyUserListProducts(2, "Shirt");

        for (UserListProduct userListProduct : userListProducts) {
            try {
                userList.addProduct(userListProduct, 2);
            } catch (SizeLimitExceededException e) {
                // do not expect this happen here
                e.printStackTrace();
            } catch (ProductAlreadyExistsException e) {
                // do not expect this happen here
                e.printStackTrace();
            }
        }

        return userList;
    }

    private List<UserListProduct> getDummyUserListProducts(int count, String startsWIth) {
        List<UserListProduct> productList = new ArrayList<>();
        for (int i=0; i<count; i++) {
            String productId = startsWIth+"Id_"+i;
            String productTitle = startsWIth+"Title_"+i;
            UserListProduct product = new UserListProduct(productId, productTitle);
            productList.add(product);
        }
        return productList;
    }
}
