package se.jfs.test.dbcartapi.cart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.jfs.test.dbcartapi.cart.model.Cart;
import se.jfs.test.dbcartapi.cart.model.CartProduct;
import se.jfs.test.dbcartapi.cart.model.CartProductRepository;
import se.jfs.test.dbcartapi.cart.model.CartRepository;
import se.jfs.test.dbcartapi.product.model.Product;
import se.jfs.test.dbcartapi.product.model.ProductRepository;

import java.util.List;

@Service
public class CartService {
@Autowired
CartRepository repo;
@Autowired
ProductRepository productRepo;
@Autowired
CartProductRepository cpRepo;


    public Cart saveCart(Cart cart) {
      return repo.saveCart(cart);
    }


    public CartProduct saveCartProduct(CartProduct newCartProduct) {
      return cpRepo.saveProduct(newCartProduct);
    }

    public CartDTO UpdateCart(String cartID, String productId, long quantity) {
        Cart savedCart = repo.getCart(cartID);
        Product product= productRepo.getProduct(productId);
        savedCart.setTotal(savedCart.getTotal()+quantity);
        savedCart.setPrice(savedCart.getPrice()+product.getPrice()*quantity);
        Cart cart= repo.saveCart(savedCart);
        return ToDTO(cart.getCartId());
    }

    public void updateCartProduct(String productId, String cartID,long quantity) {
        CartProduct savedCartProduct=cpRepo.GetCartProduct(productId,cartID);
        savedCartProduct.setQuantity(savedCartProduct.getQuantity()+quantity);
        cpRepo.saveProduct(savedCartProduct);
    }

    public CartDTO getCart(String cartID){
        return ToDTO(cartID);
    }

    public CartDTO ToDTO(String cartID){
        List<CartProductDTO> listCartProduct;
        listCartProduct=cpRepo.FindProductsInCart(cartID);
        Cart cart= repo.getCart(cartID);
        return new CartDTO(cartID,listCartProduct,cart.getTotal(),cart.getPrice());
    }

    public void deleteCart(String cartId) {
       cpRepo.deleteCartProducts(cartId);
       repo.deleteCartFromTable(repo.getCart(cartId));
    }

    public boolean CheckProductIsValid(String productId) {
       return productRepo.CheckIfProductExists(productId);
    }

    public boolean CheckCartIsExisting(String cartID) {
        return repo.checkIfCartExists(cartID);
    }

    public boolean CheckProductInCart(String productId, String cartID) {
        return cpRepo.checkIfProductAlreadyInCart(productId, cartID);
    }



}
