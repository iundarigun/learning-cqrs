package br.com.devcave.cqrs.product.core.repository

import br.com.devcave.cqrs.product.core.domain.entity.Product
import org.springframework.data.jpa.repository.JpaRepository

interface ProductRepository : JpaRepository<Product, String> {
    fun findByProductId(productId: String): Product
}