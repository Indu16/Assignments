package se.jfs.test.dbcartapi.cart;
import se.jfs.test.dbcartapi.cart.model.CartProduct;
import java.util.UUID;

public class ModelDTOConverter {
    static CartProduct fromDto(AddCartProductDTO dto,String cartID){
        return new CartProduct((String.valueOf(UUID.randomUUID())), dto.productId(), dto.quantity(),cartID);
    }

}

