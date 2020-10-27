package com.japp.list.service;

import com.japp.list.model.UserList;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserListService {

    public List<UserList> getLists(String profileId);

}
