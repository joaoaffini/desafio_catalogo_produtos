package com.compasso.uol.catalogo.produto.dto;

import java.math.BigDecimal;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ProductDTO {
	
	@NotEmpty(message = "name é obrigatório")
	private String name;
	
	@NotEmpty(message = "description é obrigatório")
	private String description;
	
	@Positive(message = "price deve ser um número positivo")
	@NotNull(message = "price é obrigatório")
	private BigDecimal price;

}
