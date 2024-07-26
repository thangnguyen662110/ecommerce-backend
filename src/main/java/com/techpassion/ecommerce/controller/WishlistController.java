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
import com.techpassion.ecommerce.entity.Wishlist;
import com.techpassion.ecommerce.repository.WishlistRepository;

@RestController
@RequestMapping("/ecommerce/wishlist")
public class WishlistController {
	private final WishlistRepository wishlistRepository;
	
	public WishlistController(WishlistRepository wishlistRepository) {
		this.wishlistRepository = wishlistRepository;
	}
	
	@GetMapping
	public List<Wishlist> getWishlists() {
		return wishlistRepository.findAll();
	}
	
	@GetMapping("/{wishlist_id}")
	public Wishlist getWishlist(@PathVariable Integer wishlist_id) {
		return wishlistRepository.findById(wishlist_id).orElseThrow(RuntimeException::new);
	}
	
	@PutMapping("/{wishlist_id}")
	public ResponseEntity updateWishlist(@PathVariable Integer wishlist_id, @RequestBody Wishlist wishlist) {
		Wishlist currentWishlist = wishlistRepository.findById(wishlist_id).orElseThrow(RuntimeException::new);
		currentWishlist.setWishlist_id(wishlist.getWishlist_id());
		currentWishlist = wishlistRepository.save(currentWishlist);
		return ResponseEntity.ok(currentWishlist);
	}
	
	@PostMapping("/{wishlist_id}")
	public ResponseEntity createWishlist(@RequestBody Wishlist wishlist) throws URISyntaxException {
		Wishlist savedWishlist = wishlistRepository.save(wishlist);
		return ResponseEntity.created(new URI("/wishlist/" +savedWishlist.getWishlist_id())).body(savedWishlist);
	}
	
	@DeleteMapping("/{wishlist_id}")
	public ResponseEntity deleteWishlist(@PathVariable Integer wishlist_id) {
		wishlistRepository.deleteById(wishlist_id);
		return ResponseEntity.ok().build();
	}
	
}
