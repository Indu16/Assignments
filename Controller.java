package se.jfs.test.dbcartapi.cart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.jfs.test.dbcartapi.cart.model.Cart;
import se.jfs.test.dbcartapi.cart.model.CartProduct;
import java.net.URI;

@RestController
@RequestMapping("/api/carts")
public class Controller {

  @Autowired
  CartService service;

  @PostMapping
  ResponseEntity <CartDTO> createCart(){
    Cart cart= service.saveCart(new Cart());
    URI location = URI.create(("/api/carts/"+ cart.getCartId()));
    return ResponseEntity.created(location).body(service.ToDTO(cart.getCartId()));
  }


  @PostMapping("/{cartID}/products")
  ResponseEntity <CartDTO> addProductToCart(@RequestBody AddCartProductDTO newProduct,@PathVariable String cartID){
    boolean productExisting=service.CheckProductIsValid(newProduct.productId());
    boolean cartExisting=service.CheckCartIsExisting(cartID);
    boolean productAlreadyInCart=service.CheckProductInCart(newProduct.productId(),cartID);
    if(productExisting&&cartExisting) {
      CartDTO cartDTO;
      if(productAlreadyInCart){
        service.updateCartProduct(newProduct.productId(),cartID,newProduct.quantity());
        cartDTO = service.UpdateCart(cartID, newProduct.productId(),newProduct.quantity());
      }
      else {
        CartProduct cProd = ModelDTOConverter.fromDto(newProduct, cartID);
        CartProduct cartProduct = service.saveCartProduct(cProd);
        cartDTO = service.UpdateCart(cartID, cartProduct.getProductId(), cartProduct.getQuantity());
      }
      return ResponseEntity.status(HttpStatus.ACCEPTED).body(cartDTO);
    }
    else{
      return ResponseEntity.notFound().build();
    }
  }

  @GetMapping("/{cartID}")
  ResponseEntity <CartDTO> listCart(@PathVariable String cartID){
    if (service.CheckCartIsExisting(cartID))
  return ResponseEntity.ok().body(service.getCart(cartID));
    else
      return ResponseEntity.noContent().build();
  }

  @DeleteMapping("/{cartId}")
  ResponseEntity deleteCart(@PathVariable String cartId) {
    boolean cartExisting = service.CheckCartIsExisting(cartId);
    if (cartExisting) {
      service.deleteCart(cartId);
    }
    return ResponseEntity.noContent().build();
  }
}
