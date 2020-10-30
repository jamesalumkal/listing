package com.japp.list.service;

import com.japp.list.config.ListConfig;
import com.japp.list.dao.UserListRepository;
import com.japp.list.model.UserList;
import com.japp.list.model.UserListAccessType;
import com.japp.list.model.UserListProduct;
import com.japp.list.model.UserListType;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class UserListServiceTests {

    @InjectMocks
    private UserListServiceImpl userListService;

    @Mock
    private UserListFactory userListFactory;

    @Mock
    private ListConfig listConfig;

    @Mock
    private UserListRepository userListRepository;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    public void createEmptyUserList_return() throws Exception {
        String profileId = "ProfId100";
        String listName = "UserListName100";
        UserListAccessType userListAccessType = UserListAccessType.PUBLIC;
        UserListType userListType = UserListType.REGULAR;
        List<UserListProduct> userListProducts = new ArrayList<>();

        Mockito.when(userListFactory.createUserList(Mockito.any(), Mockito.any())).thenReturn(Mockito.mock(UserList.class));
        Mockito.when(listConfig.getAllowedsize()).thenReturn(10);
        Mockito.when(userListRepository.save(Mockito.any())).thenReturn(Mockito.mock(UserList.class));

        UserList userList = userListService.createUserList(listName, profileId, userListAccessType, userListType, userListProducts);
        assertThat(userList).isNotNull();
    }

    @Test
    public void getLists_returnAll() throws Exception {
        String profileId = "ProfId100";
        String listName = "UserListName100";
        UserListAccessType userListAccessType = UserListAccessType.PUBLIC;
        UserListType userListType = UserListType.REGULAR;
        List<UserListProduct> userListProducts = new ArrayList<>();

        Mockito.when(userListFactory.createUserList(Mockito.any(), Mockito.any())).thenReturn(Mockito.mock(UserList.class));
        Mockito.when(listConfig.getAllowedsize()).thenReturn(10);
        Mockito.when(userListRepository.save(Mockito.any())).thenReturn(Mockito.mock(UserList.class));

        List<UserList> list = new ArrayList<>();
        list.add(Mockito.mock(UserList.class));
        Mockito.when(userListRepository.findByProfileId(profileId)).thenReturn(list);

        userListService.createUserList(listName, profileId, userListAccessType, userListType, userListProducts);

        assertThat(userListService.getLists(profileId)).isNotNull();
        assertThat(userListService.getLists(profileId).size()).isEqualTo(1);

    }

}