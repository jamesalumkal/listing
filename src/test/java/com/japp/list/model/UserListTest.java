package com.japp.list.model;

import com.japp.list.exceptions.ProductAlreadyExistsException;
import com.japp.list.exceptions.SizeLimitExceededException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.ConfigFileApplicationContextInitializer;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

public class UserListTest {

    UserList userList;

    @BeforeEach
    public void initBeforeEachTest() {
        userList = new UserList();
        userList.setAllowedLimit(5);
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

        Assertions.assertEquals(userListName, userList.getListName());
        Assertions.assertEquals(userProfId, userList.getProfileId());
    }

    @Test
    public void setListType() throws Exception {
        userList.setListType(UserListType.REGULAR);
       Assertions.assertEquals(UserListType.REGULAR, userList.getListType());
    }


    @Test
    public void setListAccessType() throws Exception {
        userList.setListAccessType(UserListAccessType.PUBLIC);
        Assertions.assertEquals(UserListAccessType.PUBLIC, userList.getListAccessType());
    }

    @Test
    public void addProducts() throws Exception {
        UserListProduct product = new UserListProduct();
        userList.addProduct(product);
        Assertions.assertEquals(1, userList.getUserListProducts().size());
    }

    @Test
    public void addProductWithAlreadyExist_throwsAllreadyExists() throws Exception {
        UserListProduct product = new UserListProduct();
        product.setProductId("ProdId100");

        userList.addProduct(product);
        Assertions.assertEquals(1, userList.getUserListProducts().size());

        Assertions.assertThrows(ProductAlreadyExistsException.class, () -> {
            userList.addProduct(product);
        });
    }

    @Test
    public void removeProducts() throws Exception {
        UserListProduct product = new UserListProduct();
        product.setProductId("ProdId100");
        userList.addProduct(product);
        Assertions.assertEquals(1, userList.getUserListProducts().size());

        userList.removeProduct(product);
        Assertions.assertEquals(0, userList.getUserListProducts().size());
    }

    @Test
    public void excedLimit_throwsException() throws Exception {
        Assertions.assertThrows(SizeLimitExceededException.class, () -> {
            for (UserListProduct product : getUserListProducts(10)) {
                userList.addProduct(product);
            }
        });
    }

    private List<UserListProduct> getUserListProducts(int count) {
        List<UserListProduct> productList = new ArrayList<>();
        for (int i=0; i<count; i++) {
            UserListProduct product = new UserListProduct();
            product.setProductId("ProdId_"+i);
            product.setProductTitle("ProdTitle_"+i);

            productList.add(product);
        }

        return productList;
    }

}
