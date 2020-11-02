package com.japp.list.service;

import com.japp.list.exceptions.SizeLimitExceededException;
import com.japp.list.model.UserList;
import com.japp.list.model.UserListAccessType;
import com.japp.list.model.UserListProduct;
import com.japp.list.model.UserListType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;


@SpringBootTest
class UserListServiceIntegrationTests {

    @Autowired
    private UserListService userListService;

    @Test
    public void createEmptyUserList_returnUserList() throws Exception {
        String userListName = "UserListName";
        String profileId = "ProfId100";
        UserListType userListType = UserListType.REGULAR;
        UserListAccessType userListAccessType = UserListAccessType.PUBLIC;
        List<UserListProduct> userListProducts = new ArrayList<>();

        UserList userList = userListService.createUserList(userListName, profileId, userListAccessType, userListType, userListProducts);
        assertThat(userList.getListId()).isNotNull();
        assertThat(userList.getListName()).isEqualTo(userListName);
    }

    @Test
    public void getUserListsByProfileId_returnAllUserLists() throws Exception {
        String userListName = "UserListName";
        String profileId = "ProfId101";
        UserListType userListType = UserListType.REGULAR;
        UserListAccessType userListAccessType = UserListAccessType.PUBLIC;
        List<UserListProduct> userListProducts = new ArrayList<>();
        userListService.createUserList(userListName, profileId, userListAccessType, userListType, userListProducts);

        userListName = "UserListAnotherName";
        userListService.createUserList(userListName, profileId, userListAccessType, userListType, userListProducts);

        assertThat(userListService.getLists(profileId).size()).isEqualTo(2);
    }

    @Test
    public void getUserListByProfileIdAndListId_returnValidUserList() throws Exception {
        String userListName = "UserListName";
        String profileId = "ProfId102";
        UserListType userListType = UserListType.REGULAR;
        UserListAccessType userListAccessType = UserListAccessType.PUBLIC;
        List<UserListProduct> userListProducts = new ArrayList<>();
        UserList userListFromDB = userListService.createUserList(userListName, profileId, userListAccessType, userListType, userListProducts);

        assertThat(userListService.getList(profileId, userListFromDB.getListId()).getListName()).isEqualTo(userListName);
    }

    @Test
    public void addProductsToUserList_returnUpdatedUserList() throws Exception {
        String userListName = "UserListName";
        String profileId = "ProfId103";
        UserListType userListType = UserListType.REGULAR;
        UserListAccessType userListAccessType = UserListAccessType.PUBLIC;
        List<UserListProduct> userListProducts = getUserListProducts(2, "Shirt");
        UserList userListFromDB = userListService.createUserList(userListName, profileId, userListAccessType, userListType, userListProducts);

        assertThat(userListService.addProducts(
                profileId, userListFromDB.getListId(), getUserListProducts(2, "Book")).getUserListProducts().size()
        ).isEqualTo(4);
    }

    @Test
    public void addProductsToUserList_returnSizeExceededException() throws Exception {
        String userListName = "UserListName";
        String profileId = "ProfId104";
        UserListType userListType = UserListType.REGULAR;
        UserListAccessType userListAccessType = UserListAccessType.PUBLIC;
        List<UserListProduct> userListProducts = getUserListProducts(2, "Shirt");
        UserList userListFromDB = userListService.createUserList(userListName, profileId, userListAccessType, userListType, userListProducts);

        assertThatThrownBy(() -> {
            userListService.addProducts(
                    profileId, userListFromDB.getListId(), getUserListProducts(9, "Book"));
        }).isInstanceOf(SizeLimitExceededException.class).hasMessage("Size Limit Exceeded!");

        assertThat(userListService.getList(profileId, userListFromDB.getListId()).getUserListProducts().size()).isEqualTo(2);
    }


    private List<UserListProduct> getUserListProducts(int count, String startsWIth) {
        List<UserListProduct> productList = new ArrayList<>();
        for (int i=0; i<count; i++) {
            UserListProduct product = new UserListProduct();
            product.setProductId(startsWIth+"Id_"+i);
            product.setProductTitle(startsWIth+"Title_"+i);
            productList.add(product);
        }
        return productList;
    }

}