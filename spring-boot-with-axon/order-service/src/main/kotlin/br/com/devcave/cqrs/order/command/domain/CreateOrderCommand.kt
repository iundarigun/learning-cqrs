package br.com.devcave.cqrs.order.command.domain

import org.axonframework.modelling.command.TargetAggregateIdentifier

data class CreateOrderCommand(
    @TargetAggregateIdentifier
    val orderId: String,
    val productId: String,
    val quantity: Int,
    val addressId: String,
    val orderStatus: OrderStatus = OrderStatus.CREATED,
    val userId: String = "27b95829-4f3f-4ddf-8983-151ba010e35b"
)
