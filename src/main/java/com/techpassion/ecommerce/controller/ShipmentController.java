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
import com.techpassion.ecommerce.entity.Shipment;
import com.techpassion.ecommerce.repository.ShipmentRepository;

@RestController
@RequestMapping("/ecommerce/shipment")
public class ShipmentController {
	private final ShipmentRepository shipmentRepository;
	
	public ShipmentController(ShipmentRepository shipmentRepository) {
		this.shipmentRepository = shipmentRepository;
	}
	
	@GetMapping
	public List<Shipment> getShipments() {
		return shipmentRepository.findAll();
	}
	
	@GetMapping("/{shipment_id}")
	public Shipment getShipment(@PathVariable Integer shipment_id) {
		return shipmentRepository.findById(shipment_id).orElseThrow(RuntimeException::new);
	}
	
	@PutMapping("/{shipment_id}")
	public ResponseEntity updateShipment(@PathVariable Integer shipment_id, @RequestBody Shipment shipment) {
		Shipment currentShipment = shipmentRepository.findById(shipment_id).orElseThrow(RuntimeException::new);
		currentShipment.setAddress(shipment.getAddress());
		currentShipment.setCity(shipment.getCity());
		currentShipment.setCountry(shipment.getCountry());
		currentShipment.setShipment_date(shipment.getShipment_date());
		currentShipment.setState(shipment.getState());
		currentShipment.setZip_code(shipment.getZip_code());
		currentShipment = shipmentRepository.save(currentShipment);
		return ResponseEntity.ok(currentShipment);
	}
	
	@PostMapping("/{shipment_id}")
	public ResponseEntity createShipment(@RequestBody Shipment shipment) throws URISyntaxException {
		Shipment savedShipment = shipmentRepository.save(shipment);
		return ResponseEntity.created(new URI("/shipment/" +savedShipment.getShipment_id())).body(savedShipment);
	}
	
	@DeleteMapping("/{shipment_id}")
	public ResponseEntity deleteShipment(@PathVariable Integer shipment_id) {
		shipmentRepository.deleteById(shipment_id);
		return ResponseEntity.ok().build();
	}
	
}
