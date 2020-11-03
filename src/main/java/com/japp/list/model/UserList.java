package com.japp.list.model;

import com.japp.list.exceptions.ProductAlreadyExistsException;
import com.japp.list.exceptions.SizeLimitExceededException;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

/**
 * Root entity of the UserList Aggregate
 */
@Data // Mutable, AllArgsConstructor
@Document("UserList")
public class UserList {

    @Id
    private String listId;
    private String listName;
    private String profileId;
    private UserListType listType;
    private UserListAccessType listAccessType;

    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    private List<UserListProduct> userListProducts;
    public List<UserListProduct> getUserListProducts() {
        return new ArrayList<>(userListProducts);
    }

    public UserList() {
        userListProducts = new ArrayList<>();
    }

    /**
     * Add products to the userList if business validations passed
     */
    public boolean addProduct(UserListProduct prod, int allowedsize)
            throws SizeLimitExceededException, ProductAlreadyExistsException {

        if (userListProducts.size() >= allowedsize)
            throw new SizeLimitExceededException();

        if (userListProducts.stream().anyMatch( p -> p.getProductId().equals(prod.getProductId())))
            throw new ProductAlreadyExistsException();

        userListProducts.add(prod);
        return true;
    }

    /**
     * remove products from the userList
     */
    public void removeProduct(UserListProduct product) {
        userListProducts.removeIf(p -> p.getProductId().equals(product.getProductId()));
    }
}
