package com.japp.list.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserListProductTest {

    @Test
    public void create_emptyUserList() throws Exception {
        UserListProduct prod = new UserListProduct();
        final String productId = "PrdId100";
        final String productTitle = "Prd100Title";
        prod.setProductId(productId);
        prod.setProductTitle(productTitle);

        Assertions.assertEquals(productId, prod.getProductId());
        Assertions.assertEquals(productTitle, prod.getProductTitle());
    }
}