package com.techpassion.ecommerce.entity;

import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "shipments")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Shipment {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer shipment_id;
	private Date shipment_date;
	private String address;
	private String city;
	private String state;
	private String country;
	private String zip_code;
}
