package se.jfs.test.dbcartapi.cart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.jfs.test.dbcartapi.cart.model.CartRepository;
import se.jfs.test.dbcartapi.product.model.ProductRepository;

@Service
public class CartService {
  @Autowired
  CartRepository repo;
  @Autowired
  ProductRepository productRepo;

}
