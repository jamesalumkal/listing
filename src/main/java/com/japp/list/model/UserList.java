package com.japp.list.model;

import com.japp.list.config.ListConfig;
import com.japp.list.exceptions.ProductAlreadyExistsException;
import com.japp.list.exceptions.SizeLimitExceededException;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Root entity of the UserList Aggregate
 */

@Data
public class UserList {

    private String listName;
    private String profileId;
    private UserListType listType;
    private UserListAccessType listAccessType;
    private List<UserListProduct> userListProducts;
    @Value("${userlist.allowedsize : 5 }")
    private int allowedLimit;

    public UserList() {
        userListProducts = new ArrayList<>();
    }

    public boolean addProduct(UserListProduct prod) throws SizeLimitExceededException, ProductAlreadyExistsException {

        if (userListProducts.size() == allowedLimit)
            throw new SizeLimitExceededException();

        if (userListProducts.stream().anyMatch( p -> p.getProductId().equals(prod.getProductId())))
            throw new ProductAlreadyExistsException();

        userListProducts.add(prod);
        return true;
    }

    public void removeProduct(UserListProduct product) {
        userListProducts.removeIf(p -> p.getProductId().equals(product.getProductId()));
    }
}
