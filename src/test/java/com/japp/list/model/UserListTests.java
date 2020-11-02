package com.japp.list.model;

import com.japp.list.exceptions.ProductAlreadyExistsException;
import com.japp.list.exceptions.SizeLimitExceededException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class UserListTests {

    UserList userList;

    @BeforeEach
    public void initBeforeEachTest() {
        userList = new UserList();
    }

    @Test
    public void create_emptyUserList() throws Exception {
        UserList userList = new UserList();
    }

    @Test
    public void setNameAndProfileId() throws Exception {
        final String userListName = "UserListName";
        final String userProfId = "ProfId100";

        userList.setListName(userListName);
        userList.setProfileId(userProfId);

        assertThat(userList.getListName()).isEqualTo(userListName);
        assertThat(userList.getProfileId()).isEqualTo(userProfId);
    }

    @Test
    public void setListType() throws Exception {
        userList.setListType(UserListType.REGULAR);
        assertThat(userList.getListType()).isEqualTo(UserListType.REGULAR);
    }

    @Test
    public void setListAccessType() throws Exception {
        userList.setListAccessType(UserListAccessType.PUBLIC);
        assertThat(userList.getListAccessType()).isEqualTo(UserListAccessType.PUBLIC);
    }

    @Test
    public void addProducts() throws Exception {
        UserListProduct product = new UserListProduct("id", "title");
        userList.addProduct(product, 1);
        assertThat(userList.getUserListProducts()).isNotEmpty();
    }

    @Test
    public void addProductWithAlreadyExist_throwsAllreadyExists() throws Exception {
        UserListProduct product = new UserListProduct("ProdId100", "ProdTitle100");

        userList.addProduct(product, 2);
        assertThat(userList.getUserListProducts().size()).isEqualTo(1);

         assertThatThrownBy(() -> {
            userList.addProduct(product, 2);
         }).isInstanceOf(ProductAlreadyExistsException.class).hasMessage("Product already exists!");
    }

    @Test
    public void removeProducts() throws Exception {
        UserListProduct product = new UserListProduct("ProdId100", "ProdTitle100");
        userList.addProduct(product, 1);
        assertThat(userList.getUserListProducts().size()).isEqualTo(1);

        userList.removeProduct(product);
        assertThat(userList.getUserListProducts().size()).isEqualTo(0);
    }

    @Test
    public void excedLimit_throwsException() throws Exception {
        assertThatThrownBy(() -> {
            for (UserListProduct product : getUserListProducts(10)) {
                userList.addProduct(product, 5);
            }
        }).isInstanceOf(SizeLimitExceededException.class).hasMessage("Size Limit Exceeded!");
    }

    private List<UserListProduct> getUserListProducts(int count) {
        List<UserListProduct> productList = new ArrayList<>();
        for (int i=0; i<count; i++) {
            UserListProduct product = new UserListProduct("ProdId_"+i, "ProdTitle_"+i);
            productList.add(product);
        }

        return productList;
    }

}
