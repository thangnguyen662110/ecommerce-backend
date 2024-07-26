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
import com.techpassion.ecommerce.entity.Product;
import com.techpassion.ecommerce.repository.ProductRepository;

@RestController
@RequestMapping("/ecommerce/product")
public class ProductController {
	private final ProductRepository productRepository;
	
	public ProductController(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}
	
	@GetMapping
	public List<Product> getProducts() {
		return productRepository.findAll();
	}
	
	@GetMapping("/{product_id}")
	public Product getProduct(@PathVariable Integer product_id) {
		return productRepository.findById(product_id).orElseThrow(RuntimeException::new);
	}
	
	@PutMapping("/{product_id}")
	public ResponseEntity updateProduct(@PathVariable Integer product_id, @RequestBody Product product) {
		Product currentProduct = productRepository.findById(product_id).orElseThrow(RuntimeException::new);
		currentProduct.setDescription(product.getDescription());
		currentProduct.setPrice(product.getPrice());
		currentProduct.setSKU(product.getSKU());
		currentProduct.setStock(product.getStock());
		currentProduct = productRepository.save(currentProduct);
		return ResponseEntity.ok(currentProduct);
	}
	
	@PostMapping("/{product_id}")
	public ResponseEntity createProduct(@RequestBody Product product) throws URISyntaxException {
		Product savedProduct = productRepository.save(product);
		return ResponseEntity.created(new URI("/product/" +savedProduct.getProduct_id())).body(savedProduct);
	}
	
	@DeleteMapping("/{product_id}")
	public ResponseEntity deleteProduct(@PathVariable Integer product_id) {
		productRepository.deleteById(product_id);
		return ResponseEntity.ok().build();
	}
	
}
