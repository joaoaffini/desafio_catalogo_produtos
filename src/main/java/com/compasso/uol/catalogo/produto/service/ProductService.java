package com.compasso.uol.catalogo.produto.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.compasso.uol.catalogo.produto.dto.ProductDTO;
import com.compasso.uol.catalogo.produto.model.Product;
import com.compasso.uol.catalogo.produto.service.exception.DuplicateProductException;
import com.compasso.uol.catalogo.produto.service.exception.ObjectNotFoundException;
import com.compasso.uol.catalogo.produto.service.repository.ProductCustomRepository;
import com.compasso.uol.catalogo.produto.service.repository.ProductRepository;

@Service
public class ProductService {
	
	@Autowired
	private ProductRepository repository;
	
	@Autowired
	private ProductCustomRepository customRepository;

	@Transactional
	public Product saveProduct(ProductDTO dto) {
		
		Optional<Product> prodBD = repository.findByName(dto.getName());
		
		if(prodBD.isPresent()) {
			String msg = getErrorMsg("O produto %s já está cadastrado em nossa base de dados.", dto.getName());
			throw new DuplicateProductException(msg);
		}
		
		Product prod = Product.builder()
							  .name(dto.getName())
							  .description(dto.getDescription())
							  .price(dto.getPrice())
							  .build();
		
		return repository.save(prod); 
	}

	public Product getProductById(Integer id) {
		
		Optional<Product> prod = repository.findById(id);
		
		if(!prod.isPresent()) {
			String msg = getErrorMsg("Não existe nenhum produto com o id: %s", id.toString());
			throw new ObjectNotFoundException(msg);
		}
		
		return prod.get();
	}
	
	public List<Product> getAllProducts() {
		return repository.findAll();
	}
	
	public void deleteProduct(Integer id) {
		Product prod = this.getProductById(id);
		
		repository.delete(prod);
		
	}
	
	public Product updateProduct(Integer id, ProductDTO dto) {
		Product prod = this.getProductById(id);
		
		prod.setName(dto.getName());
		prod.setDescription(dto.getDescription());
		prod.setPrice(dto.getPrice());
		
		return repository.save(prod);
		
	}
	
	private String getErrorMsg(String msg, Object param) {
		return String.format(msg, param).toString();
	}

	public List<Product> searchProduct(String q, BigDecimal min_price, BigDecimal max_price) {
		return customRepository.search(q, min_price, max_price);
	}
}
