package com.japp.list.model;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;


class UserListProductTests {

    @Test
    public void create_emptyUserList() throws Exception {
        final String productId = "PrdId100";
        final String productTitle = "Prd100Title";

        UserListProduct prod = new UserListProduct(productId, productTitle);

        assertThat(prod.getProductId()).isEqualTo(productId);
        assertThat(prod.getProductTitle()).isEqualTo(productTitle);
    }
}