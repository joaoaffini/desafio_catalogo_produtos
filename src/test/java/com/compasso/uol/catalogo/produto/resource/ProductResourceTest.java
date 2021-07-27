package com.compasso.uol.catalogo.produto.resource;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static io.restassured.module.mockmvc.RestAssuredMockMvc.standaloneSetup;
import static org.hamcrest.CoreMatchers.containsString;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;

import com.compasso.uol.catalogo.produto.dto.ProductDTO;
import com.compasso.uol.catalogo.produto.model.Product;
import com.compasso.uol.catalogo.produto.service.ProductService;

import io.restassured.http.ContentType;

@WebMvcTest
public class ProductResourceTest {
	
	@Autowired
	private ProductResource productResource;
	
	@MockBean
	private ProductService productService;
	
	@BeforeEach
	public void setup() {
		standaloneSetup(this.productResource);
	}
	
	@Test
	public void deveRetornarCreated_QuandoInserirProduto() {
		ProductDTO dto = ProductDTO.builder()
				.name("Notebook")
				.description("ACER")
				.price(new BigDecimal(3785.99))
				.build();
		
		when(this.productService.saveProduct(dto))
			.thenReturn(Product.builder()
							.id(1)
							.name("Notebook")
							.description("ACER")
							.price(new BigDecimal(3785.99))
							.build());
		
		given()
			.accept(ContentType.JSON)
			.contentType(ContentType.JSON)
			.body(dto)
			.post("/products")
			.then().statusCode(HttpStatus.CREATED.value())
			.and()
			.body(containsString("id"), containsString("name"), containsString("description"),
					containsString("price"));
			
	}
	
	@Test
	public void deveRetornarOK_QuandoConsultaPorId() {
		when(this.productService.getProductById(1))
			.thenReturn(Product.builder()
					.id(1)
					.name("Notebook")
					.description("ACER")
					.price(new BigDecimal(3785.99))
					.build());
		
		given()
			.contentType(ContentType.JSON)
			.get("/products/{id}", 1)
			.then()
			.statusCode(HttpStatus.OK.value())
			.body(containsString("id"), containsString("name"), containsString("description"),
					containsString("price"));
		
		
	}

}
