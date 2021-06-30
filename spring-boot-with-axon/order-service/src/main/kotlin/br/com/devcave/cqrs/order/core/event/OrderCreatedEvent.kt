package br.com.devcave.cqrs.order.core.event

import br.com.devcave.cqrs.order.command.domain.OrderStatus

data class OrderCreatedEvent(
    val orderId: String,
    val productId: String,
    val quantity: Int,
    val addressId: String,
    val orderStatus: OrderStatus,
    val userId: String
)
