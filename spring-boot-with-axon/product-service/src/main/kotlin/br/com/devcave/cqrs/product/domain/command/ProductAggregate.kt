package br.com.devcave.cqrs.product.domain.command

import br.com.devcave.cqrs.product.event.ProductCreatedEvent
import org.axonframework.commandhandling.CommandHandler
import org.axonframework.eventsourcing.EventSourcingHandler
import org.axonframework.modelling.command.AggregateIdentifier
import org.axonframework.modelling.command.AggregateLifecycle
import org.axonframework.spring.stereotype.Aggregate
import java.math.BigDecimal

@Aggregate
class ProductAggregate() {

    @AggregateIdentifier
    var productId: String? = null
    var title: String? = null
    var price: BigDecimal? = null
    var quantity: Int? = null

    @CommandHandler
    constructor(createProductCommand: CreateProductCommand) : this() {
        // validate create product command: This parts can be on request body validation ...
        if (createProductCommand.price <= BigDecimal.ZERO) {
            throw IllegalArgumentException("Price cannot be less or equal than zero")
        }
        if (createProductCommand.title.isBlank()) {
            throw IllegalArgumentException("Title cannot be empty")
        }

        val event = createProductCommand.toProductCreatedEvent()

        AggregateLifecycle.apply(event)
    }

    @EventSourcingHandler
    fun on(productCreatedEvent: ProductCreatedEvent) {
        productId = productCreatedEvent.productId
        title = productCreatedEvent.title
        price = productCreatedEvent.price
        quantity = productCreatedEvent.quantity
    }
}

private fun CreateProductCommand.toProductCreatedEvent(): ProductCreatedEvent =
    ProductCreatedEvent(
        productId = this.productId,
        title = this.title,
        price = this.price,
        quantity = this.quantity
    )
