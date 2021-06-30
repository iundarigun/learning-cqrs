package br.com.devcave.cqrs.order.command

import br.com.devcave.cqrs.order.command.domain.CreateOrderCommand
import br.com.devcave.cqrs.order.command.domain.OrderStatus
import br.com.devcave.cqrs.order.core.event.OrderCreatedEvent
import org.axonframework.commandhandling.CommandHandler
import org.axonframework.eventsourcing.EventSourcingHandler
import org.axonframework.modelling.command.AggregateIdentifier
import org.axonframework.modelling.command.AggregateLifecycle
import org.axonframework.spring.stereotype.Aggregate
import org.slf4j.LoggerFactory

@Aggregate
class OrderAggregate() {
    private val logger = LoggerFactory.getLogger(javaClass)

    @AggregateIdentifier
    var orderId: String? = null
    var productId: String? = null
    var quantity: Int? = null
    var addressId: String? = null
    var orderStatus: OrderStatus? = null
    var userId: String? = null

    @CommandHandler
    constructor(createOrderCommand: CreateOrderCommand) : this() {
        logger.info("constructor, $createOrderCommand")

        // Forcing error to see exception handler in action
        if (createOrderCommand.quantity == 13) {
            throw IllegalArgumentException("Quantity is 13 and can not be. Forcing exception")
        }

        val event = createOrderCommand.toOrderCreatedEvent()

        AggregateLifecycle.apply(event)
    }

    @EventSourcingHandler
    fun on(event: OrderCreatedEvent) {
        logger.info("on, $event")
        orderId = event.orderId
        productId = event.productId
        quantity = event.quantity
        addressId = event.addressId
        orderStatus = event.orderStatus
        userId = event.userId
    }
}

private fun CreateOrderCommand.toOrderCreatedEvent(): OrderCreatedEvent =
    OrderCreatedEvent(
        orderId = this.orderId,
        productId = this.productId,
        quantity = this.quantity,
        addressId = this.addressId,
        orderStatus = this.orderStatus,
        userId = this.userId
    )
