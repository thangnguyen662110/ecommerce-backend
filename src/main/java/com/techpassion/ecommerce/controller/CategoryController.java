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
import com.techpassion.ecommerce.entity.Category;
import com.techpassion.ecommerce.repository.CategoryRepository;

@RestController
@RequestMapping("/ecommerce/category")
public class CategoryController {
	private final CategoryRepository categoryRepository;
	
	public CategoryController(CategoryRepository categoryRepository) {
		this.categoryRepository = categoryRepository;
	}
	
	@GetMapping
	public List<Category> getCategorys() {
		return categoryRepository.findAll();
	}
	
	@GetMapping("/{category_id}")
	public Category getCategory(@PathVariable Integer category_id) {
		return categoryRepository.findById(category_id).orElseThrow(RuntimeException::new);
	}
	
	@PutMapping("/{category_id}")
	public ResponseEntity updateCategory(@PathVariable Integer category_id, @RequestBody Category category) {
		Category currentCategory = categoryRepository.findById(category_id).orElseThrow(RuntimeException::new);
		currentCategory.setName(category.getName());
		currentCategory = categoryRepository.save(currentCategory);
		return ResponseEntity.ok(currentCategory);
	}
	
	@PostMapping("/{category_id}")
	public ResponseEntity createCategory(@RequestBody Category category) throws URISyntaxException {
		Category savedCategory = categoryRepository.save(category);
		return ResponseEntity.created(new URI("/category/" +savedCategory.getCategory_id())).body(savedCategory);
	}
	
	@DeleteMapping("/{category_id}")
	public ResponseEntity deleteCategory(@PathVariable Integer category_id) {
		categoryRepository.deleteById(category_id);
		return ResponseEntity.ok().build();
	}
	
}
