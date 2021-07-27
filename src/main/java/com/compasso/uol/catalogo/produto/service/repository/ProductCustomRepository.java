package com.compasso.uol.catalogo.produto.service.repository;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import com.compasso.uol.catalogo.produto.model.Product;

@Repository
public class ProductCustomRepository {

	private final EntityManager em;
	
	public ProductCustomRepository(EntityManager em) {
		this.em = em;
	}
	
	@SuppressWarnings("unchecked")
	public List<Product> search(String nameOrDescription, BigDecimal minPrice,
			BigDecimal maxPrice) {
		
		StringBuilder sql = new StringBuilder();
		String condicao = " where ";
		
		sql.append(" select p from Product p ");
		
		if(StringUtils.isNotEmpty(nameOrDescription)) {
			sql.append(condicao).append(" p.name like (:nameOrDescription) or p.description like (:nameOrDescription) ");
			condicao = " and ";
		}
		if(minPrice != null) {
			sql.append(condicao).append(" p.price >= :minPrice ");
			condicao = " and ";
		}
		if(maxPrice != null) {
			sql.append(condicao).append(" p.price <= :maxPrice ");
			condicao = " and ";
		}
		
		Query query = em.createQuery(sql.toString(), Product.class);
		
		if(StringUtils.isNotEmpty(nameOrDescription)) {
			query.setParameter("nameOrDescription", "%"+nameOrDescription+"%");
		}
		if(minPrice != null) {
			query.setParameter("minPrice", minPrice);
		}
		if(maxPrice != null) {
			query.setParameter("maxPrice", maxPrice);
		}
		
		return query.getResultList();
	}
}
