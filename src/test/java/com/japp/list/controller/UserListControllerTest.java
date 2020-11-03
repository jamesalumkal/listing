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
        UserList userList1 = getDummyUserList("ProfileId100", "MyBlackFridayList");
        UserList userList2 = getDummyUserList("ProfileId100", "MyBackToSchoolList");

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
        UserList userList = getDummyUserList("ProfileId100", "MyBlackFridayList");

        Mockito.when(userListService.createUserList(userList.getListName(), userList.getProfileId(), userList.getListAccessType(),
                userList.getListType(), userList.getUserListProducts())).thenReturn(userList);
        Mockito.when(userListFactory.createUserList(Mockito.any(), Mockito.any())).thenReturn(userList);
        Mockito.when(userListRepository.save(Mockito.any())).thenReturn(userList);

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson=ow.writeValueAsString(userList);


        mockMvc.perform(MockMvcRequestBuilders.post("/userList")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestJson))
                .andExpect(MockMvcResultMatchers.status().is(200));

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