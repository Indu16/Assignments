package se.jfs.test.dbcartapi.cart;

import com.fasterxml.jackson.annotation.JsonProperty;

public record AddCartProductDTO(@JsonProperty String productId,@JsonProperty int quantity) {
}
