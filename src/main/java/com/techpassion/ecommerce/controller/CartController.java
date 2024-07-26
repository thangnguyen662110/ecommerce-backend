package com.techpassion.ecommerce.controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.techpassion.ecommerce.entity.Cart;
import com.techpassion.ecommerce.repository.CartRepository;

@RestController
@RequestMapping("/ecommerce/cart")
public class CartController {
	private final CartRepository cartRepository;
	
	public CartController(CartRepository cartRepository) {
		this.cartRepository = cartRepository;
	}
	
	@GetMapping
	public List<Cart> getCarts() {
		return cartRepository.findAll();
	}
	
	@GetMapping("/{cart_id}")
	public Cart getCart(@PathVariable Integer cart_id) {
		return cartRepository.findById(cart_id).orElseThrow(RuntimeException::new);
	}
	
	@PutMapping("/{cart_id}")
	public ResponseEntity updateCart(@PathVariable Integer cart_id, @RequestBody Cart Cart) {
		Cart currentCart = cartRepository.findById(cart_id).orElseThrow(RuntimeException::new);
		currentCart.setQuantity(Cart.getQuantity());
		currentCart = cartRepository.save(currentCart);
		return ResponseEntity.ok(currentCart);
	}
	
	@PostMapping("/{cart_id}")
	public ResponseEntity createCart(@RequestBody Cart cart) throws URISyntaxException {
		Cart savedCart = cartRepository.save(cart);
		return ResponseEntity.created(new URI("/cart/" +savedCart.getCart_id())).body(savedCart);
	}
	
	@DeleteMapping("/{cart_id}")
	public ResponseEntity deleteCart(@PathVariable Integer cart_id) {
		cartRepository.deleteById(cart_id);
		return ResponseEntity.ok().build();
	}
	
}
