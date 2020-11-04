package com.japp.list.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.japp.list.dao.UserListRepository;
import com.japp.list.exceptions.ProductAlreadyExistsException;
import com.japp.list.exceptions.SizeLimitExceededException;
import com.japp.list.model.UserList;
import com.japp.list.model.UserListAccessType;
import com.japp.list.model.UserListProduct;
import com.japp.list.model.UserListType;
import com.japp.list.service.UserListFactory;
import com.japp.list.service.UserListService;
import com.japp.list.service.UserListServiceImpl;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@WebMvcTest(controllers = UserListController.class)
class UserListControllerTest {

    @MockBean
    private UserListServiceImpl userListService;

    @MockBean
    private UserListFactory userListFactory;

    @MockBean
    private UserListRepository userListRepository;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getAllUserListsForAProfile_returnsAll() throws Exception {
        UserList userList1 = getDummyUserList("ProfileId100", "MyBlackFridayList", "Shirt");
        UserList userList2 = getDummyUserList("ProfileId100", "MyBackToSchoolList", "Shirt");

        Mockito.when(userListService.getLists("ProfileId100")).thenReturn(Arrays.asList(userList1, userList2));

        mockMvc.perform(MockMvcRequestBuilders.get("/userList/profileId/{profileId}", "ProfileId100"))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.size()", Matchers.is(2)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].profileId", Matchers.is("ProfileId100")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].listName", Matchers.is("MyBlackFridayList")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].profileId", Matchers.is("ProfileId100")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].listName", Matchers.is("MyBackToSchoolList")));

    }

    @Test
    public void createUserList_return() throws Exception {
        UserList userList = getDummyUserList("ProfileId100", "MyBlackFridayList", "Shirt");

        Mockito.when(userListService.createUserList(userList.getListName(), userList.getProfileId(), userList.getListAccessType(),
                userList.getListType(), userList.getUserListProducts())).thenReturn(userList);
        Mockito.when(userListFactory.createUserList(Mockito.any(), Mockito.any())).thenReturn(userList);
        Mockito.when(userListRepository.save(Mockito.any())).thenReturn(userList);

        mockMvc.perform(MockMvcRequestBuilders.post("/userList")
                .contentType(MediaType.APPLICATION_JSON)
                .content(getJsonOf(userList)))
                .andExpect(MockMvcResultMatchers.status().is(200));
    }


    @Test
    public void addProductsToUserList_return() throws Exception {
        UserList userList = getDummyUserList("ProfileId100", "MyBlackFridayList", "Books");
        List<UserListProduct> userListProducts = getDummyUserListProducts(3, "Backpack");
        for (UserListProduct product: userListProducts) {
            userList.addProduct(product, 10);
        }

        Mockito.when(userListService.addProducts(Mockito.any(), Mockito.any(), Mockito.anyList())).thenReturn(userList);
        Mockito.when(userListRepository.findByProfileIdAndListId(Mockito.any(), Mockito.any())).thenReturn(userList);

        mockMvc.perform(MockMvcRequestBuilders.put("/userList/userListProduct")
                .contentType(MediaType.APPLICATION_JSON)
                .content(getJsonOf(userList)))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$.userListProducts.size()", Matchers.is(5)));

    }

    private UserList getDummyUserList(String profileId, String userListName, String ProdNameStartsWith) {
        UserList userList = new UserList();
        userList.setListName(userListName);
        userList.setProfileId(profileId);
        userList.setListType(UserListType.REGULAR);
        userList.setListAccessType(UserListAccessType.PUBLIC);
        List<UserListProduct> userListProducts = getDummyUserListProducts(2, ProdNameStartsWith);

        for (UserListProduct userListProduct : userListProducts) {
            userList.addProduct(userListProduct, 2);
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

    private String getJsonOf(UserList userList) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        return ow.writeValueAsString(userList);
    }

}