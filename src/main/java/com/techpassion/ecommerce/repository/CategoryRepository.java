package com.techpassion.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.techpassion.ecommerce.entity.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer>{

}
