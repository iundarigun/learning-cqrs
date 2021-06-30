package br.com.devcave.cqrs.order.command.domain

data class OrderRequest(
    val productId: String,
    val quantity: Int,
    val addressId: String
)
