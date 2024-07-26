package com.techpassion.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.techpassion.ecommerce.entity.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer>{

}
