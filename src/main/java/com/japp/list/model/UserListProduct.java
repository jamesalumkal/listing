package com.japp.list.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * Entity in the UserList Aggregate
 */
@Data
public class UserListProduct {
    private String productId;
    private String productTitle;
}
