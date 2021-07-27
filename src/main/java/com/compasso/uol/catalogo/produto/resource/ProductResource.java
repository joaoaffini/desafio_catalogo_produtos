package com.compasso.uol.catalogo.produto.resource;

import java.math.BigDecimal;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.compasso.uol.catalogo.produto.dto.ProductDTO;
import com.compasso.uol.catalogo.produto.model.Product;
import com.compasso.uol.catalogo.produto.service.ProductService;

@RestController
@RequestMapping("/products")
public class ProductResource {
	
	@Autowired
	private ProductService service;
	
	@PostMapping
	@ResponseStatus(value = HttpStatus.CREATED)
	public Product save(@RequestBody @Valid ProductDTO dto) {
		return service.saveProduct(dto);
	}
	
	@GetMapping
	public List<Product> getAll() {
		return service.getAllProducts();
	}
	
	@GetMapping("/{id}")
	@ResponseStatus(value = HttpStatus.OK)
	public Product getById(@PathVariable("id") Integer id) {
		return service.getProductById(id);
	}
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable("id") Integer id) {
		service.deleteProduct(id);
	}
	
	@PutMapping("/{id}")
	public Product update(@PathVariable("id") Integer id, @RequestBody @Valid ProductDTO dto) {
		
		return service.updateProduct(id, dto);
	}
	
	@GetMapping("/search")
	public List<Product> search(@RequestParam(required = false) String q, 
			@RequestParam(required = false) BigDecimal min_price, 
			@RequestParam(required = false) BigDecimal max_price) {
		
		return service.searchProduct(q, min_price, max_price);
	}

}
