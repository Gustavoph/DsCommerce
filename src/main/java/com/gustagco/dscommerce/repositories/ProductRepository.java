package com.gustagco.dscommerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gustagco.dscommerce.entities.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {}
