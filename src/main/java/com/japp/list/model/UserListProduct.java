package com.japp.list.model;

import lombok.Value;

/**
 * Value Object in the UserList Aggregate
 */
@Value // Immutable with equals and hashCode implemented
public class UserListProduct {
    String productId;
    String productTitle;
}
