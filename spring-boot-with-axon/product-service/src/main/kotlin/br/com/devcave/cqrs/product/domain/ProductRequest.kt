package br.com.devcave.cqrs.product.domain

import java.math.BigDecimal

data class ProductRequest(
    val title: String,
    val price: BigDecimal,
    val quantity: Int
)
