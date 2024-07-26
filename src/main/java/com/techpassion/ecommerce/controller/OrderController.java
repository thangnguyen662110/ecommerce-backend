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
import com.techpassion.ecommerce.entity.Order;
import com.techpassion.ecommerce.repository.OrderRepository;

@RestController
@RequestMapping("/ecommerce/order")
public class OrderController {
	private final OrderRepository orderRepository;
	
	public OrderController(OrderRepository orderRepository) {
		this.orderRepository = orderRepository;
	}
	
	@GetMapping
	public List<Order> getOrders() {
		return orderRepository.findAll();
	}
	
	@GetMapping("/{order_id}")
	public Order getOrder(@PathVariable Integer order_id) {
		return orderRepository.findById(order_id).orElseThrow(RuntimeException::new);
	}
	
	@PutMapping("/{order_id}")
	public ResponseEntity updateOrder(@PathVariable Integer order_id, @RequestBody Order order) {
		Order currentOrder = orderRepository.findById(order_id).orElseThrow(RuntimeException::new);
		currentOrder.setOrder_date(order.getOrder_date());
		currentOrder.setTotal_price(order.getTotal_price());
		currentOrder = orderRepository.save(currentOrder);
		return ResponseEntity.ok(currentOrder);
	}
	
	@PostMapping("/{order_id}")
	public ResponseEntity createOrder(@RequestBody Order order) throws URISyntaxException {
		Order savedOrder = orderRepository.save(order);
		return ResponseEntity.created(new URI("/order/" +savedOrder.getOrder_id())).body(savedOrder);
	}
	
	@DeleteMapping("/{order_id}")
	public ResponseEntity deleteOrder(@PathVariable Integer order_id) {
		orderRepository.deleteById(order_id);
		return ResponseEntity.ok().build();
	}
	
}
