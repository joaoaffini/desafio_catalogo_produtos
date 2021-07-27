package com.compasso.uol.catalogo.produto.service.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.compasso.uol.catalogo.produto.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

	public Optional<Product> findByName(String name);
}
