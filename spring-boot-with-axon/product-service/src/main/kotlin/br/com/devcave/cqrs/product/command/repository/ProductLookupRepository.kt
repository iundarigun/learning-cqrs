package br.com.devcave.cqrs.product.command.repository

import br.com.devcave.cqrs.product.command.domain.entity.ProductLookup
import org.springframework.data.jpa.repository.JpaRepository

interface ProductLookupRepository : JpaRepository<ProductLookup, String> {
    fun existsByProductIdOrTitle(productId: String, title: String): Boolean
}