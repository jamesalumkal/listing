package com.japp.list.controller;

import com.japp.list.model.UserList;
import com.japp.list.service.UserListService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/userList")
@AllArgsConstructor
public class UserListController {

    private final UserListService userListService;

    @GetMapping(path = "/profileId/{profileId}")
    public ResponseEntity<List<UserList>> getUserLists(@PathVariable String profileId) {
        return ResponseEntity.status(HttpStatus.OK).body(userListService.getLists(profileId));
    }

    @PostMapping
    public ResponseEntity<UserList> createUserList(@RequestBody UserList userList) {
        UserList userListResult = userListService.createUserList(userList.getListName(),
                userList.getProfileId(), userList.getListAccessType(),
                userList.getListType(), userList.getUserListProducts());
        return ResponseEntity.status(HttpStatus.OK).body(userListResult);
    }

    @PutMapping(path = "/userListProduct")
    public ResponseEntity<UserList> addProductsToUserList(@RequestBody UserList userList) {
        UserList userListResult = userListService.addProducts(userList.getProfileId(), userList.getListId(), userList.getUserListProducts());
        return ResponseEntity.status(HttpStatus.OK).body(userListResult);
    }

    @DeleteMapping(path = "/userListProduct")
    public ResponseEntity<UserList> removeProductsToUserList(@RequestBody UserList userList) {
        UserList userListResult = userListService.removeProducts(userList.getProfileId(), userList.getListId(), userList.getUserListProducts());
        return ResponseEntity.status(HttpStatus.OK).body(userListResult);
    }

}
