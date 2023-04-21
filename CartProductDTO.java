package se.jfs.test.dbcartapi.cart;

public record CartProductDTO(String productId, String name, int quantity, double price) {
}
