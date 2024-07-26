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
import com.techpassion.ecommerce.entity.Payment;
import com.techpassion.ecommerce.repository.PaymentRepository;

@RestController
@RequestMapping("/ecommerce/payment")
public class PaymentController {
	private final PaymentRepository paymentRepository;
	
	public PaymentController(PaymentRepository paymentRepository) {
		this.paymentRepository = paymentRepository;
	}
	
	@GetMapping
	public List<Payment> getPayments() {
		return paymentRepository.findAll();
	}
	
	@GetMapping("/{payment_id}")
	public Payment getPayment(@PathVariable Integer payment_id) {
		return paymentRepository.findById(payment_id).orElseThrow(RuntimeException::new);
	}
	
	@PutMapping("/{payment_id}")
	public ResponseEntity updatePayment(@PathVariable Integer payment_id, @RequestBody Payment payment) {
		Payment currentPayment = paymentRepository.findById(payment_id).orElseThrow(RuntimeException::new);
		currentPayment.setAmount(payment.getAmount());
		currentPayment.setPayment_date(payment.getPayment_date());
		currentPayment.setPayment_method(payment.getPayment_method());
		currentPayment = paymentRepository.save(currentPayment);
		return ResponseEntity.ok(currentPayment);
	}
	
	@PostMapping("/{payment_id}")
	public ResponseEntity createPayment(@RequestBody Payment payment) throws URISyntaxException {
		Payment savedPayment = paymentRepository.save(payment);
		return ResponseEntity.created(new URI("/payment/" +savedPayment.getPayment_id())).body(savedPayment);
	}
	
	@DeleteMapping("/{payment_id}")
	public ResponseEntity deletePayment(@PathVariable Integer payment_id) {
		paymentRepository.deleteById(payment_id);
		return ResponseEntity.ok().build();
	}
	
}
