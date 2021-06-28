package br.com.devcave.cqrs.product.query.domain

import java.math.BigDecimal

data class ProductResponse(
    val productId: String,
    val title: String,
    val price: BigDecimal,
    val quantity: Int
)
