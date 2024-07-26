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
import com.techpassion.ecommerce.entity.Customer;
import com.techpassion.ecommerce.repository.CustomerRepository;

@RestController
@RequestMapping("/ecommerce/customer")
public class CustomerController {
	private final CustomerRepository customerRepository;
	
	public CustomerController(CustomerRepository customerRepository) {
		this.customerRepository = customerRepository;
	}
	
	@GetMapping
	public List<Customer> getCustomers() {
		return customerRepository.findAll();
	}
	
	@GetMapping("/{customer_id}")
	public Customer getCustomer(@PathVariable Integer customer_id) {
		return customerRepository.findById(customer_id).orElseThrow(RuntimeException::new);
	}
	
	@PutMapping("/{customer_id}")
	public ResponseEntity updateCustomer(@PathVariable Integer customer_id, @RequestBody Customer customer) {
		Customer currentCustomer = customerRepository.findById(customer_id).orElseThrow(RuntimeException::new);
		currentCustomer.setFirst_name(customer.getFirst_name());
		currentCustomer.setLast_name(customer.getLast_name());
		currentCustomer.setEmail(customer.getEmail());
		currentCustomer.setPassword(customer.getPassword());
		currentCustomer.setAddress(customer.getAddress());
		currentCustomer.setPhone_number(customer.getPhone_number());
		currentCustomer = customerRepository.save(currentCustomer);
		return ResponseEntity.ok(currentCustomer);
	}
	
	@PostMapping("/{customer_id}")
	public ResponseEntity createCustomer(@RequestBody Customer customer) throws URISyntaxException {
		Customer savedCustomer = customerRepository.save(customer);
		return ResponseEntity.created(new URI("/customer/" +savedCustomer.getCustomer_id())).body(savedCustomer);
	}
	
	@DeleteMapping("/{customer_id}")
	public ResponseEntity deleteCustomer(@PathVariable Integer customer_id) {
		customerRepository.deleteById(customer_id);
		return ResponseEntity.ok().build();
	}
	
}
