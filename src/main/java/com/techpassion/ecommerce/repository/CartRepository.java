package com.techpassion.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.techpassion.ecommerce.entity.Cart;

@Repository
public interface CartRepository extends JpaRepository<Cart, Integer>{

}
