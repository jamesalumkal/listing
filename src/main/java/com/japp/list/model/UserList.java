package com.japp.list.model;

import com.japp.list.config.ListConfig;
import com.japp.list.exceptions.ProductAlreadyExistsException;
import com.japp.list.exceptions.SizeLimitExceededException;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


/**
 * Root entity of the UserList Aggregate
 */

@Data
@Document("UserList")
public class UserList {

    @Id
    private String listId;
    private String listName;
    private String profileId;
    private UserListType listType;
    private UserListAccessType listAccessType;
    private List<UserListProduct> userListProducts;

    public UserList() {
        userListProducts = new ArrayList<>();
    }

    public boolean addProduct(UserListProduct prod, int allowedsize)
            throws SizeLimitExceededException, ProductAlreadyExistsException {

        if (userListProducts.size() >= allowedsize)
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
