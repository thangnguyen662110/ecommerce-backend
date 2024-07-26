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
import com.techpassion.ecommerce.entity.OrderItem;
import com.techpassion.ecommerce.repository.OrderItemRepository;

@RestController
@RequestMapping("/ecommerce/order-items")
public class OrderItemController {
	private final OrderItemRepository orderItemRepository;
	
	public OrderItemController(OrderItemRepository orderItemRepository) {
		this.orderItemRepository = orderItemRepository;
	}
	
	@GetMapping
	public List<OrderItem> getOrderItems() {
		return orderItemRepository.findAll();
	}
	
	@GetMapping("/{order_item_id}")
	public OrderItem getOrderItem(@PathVariable Integer order_item_id) {
		return orderItemRepository.findById(order_item_id).orElseThrow(RuntimeException::new);
	}
	
	@PutMapping("/{order_item_id}")
	public ResponseEntity updateOrderItem(@PathVariable Integer order_item_id, @RequestBody OrderItem orderItem) {
		OrderItem currentOrderItem = orderItemRepository.findById(order_item_id).orElseThrow(RuntimeException::new);
		currentOrderItem.setPrice(orderItem.getPrice());
		currentOrderItem.setQuantity(orderItem.getQuantity());
		currentOrderItem = orderItemRepository.save(currentOrderItem);
		return ResponseEntity.ok(currentOrderItem);
	}
	
	@PostMapping("/{order_item_id}")
	public ResponseEntity createOrderItem(@RequestBody OrderItem orderItem) throws URISyntaxException {
		OrderItem savedOrderItem = orderItemRepository.save(orderItem);
		return ResponseEntity.created(new URI("/orderItem/" +savedOrderItem.getOrder_item_id())).body(savedOrderItem);
	}
	
	@DeleteMapping("/{order_item_id}")
	public ResponseEntity deleteOrderItem(@PathVariable Integer order_item_id) {
		orderItemRepository.deleteById(order_item_id);
		return ResponseEntity.ok().build();
	}
	
}
