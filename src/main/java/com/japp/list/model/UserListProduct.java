package com.japp.list.model;

import lombok.Value;

/**
 * Value Object in the UserList Aggregate
 */
@Value
public class UserListProduct {
    String productId;
    String productTitle;
}
