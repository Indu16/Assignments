package se.jfs.test.dbcartapi.cart;

import java.util.List;

public record CartDTO(String cartId, List<CartProductDTO> products, long totalNumberOfItems, double totalPrice) {
}
